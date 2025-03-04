package com.vcode.your.health.tracker.gateway_server;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
	
	@Bean
	public RouteLocator routConfig(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes()
				.route(p->p
				.path("/healthtracker/users/**").
				filters(f->f.rewritePath("/healthtracker/users/(?<segment>.*)","/${segment}")
				.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
				.circuitBreaker(config -> config.setName("usersCircuitBreaker")
						.setFallbackUri("forward:/fetchUserData"))
				.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
						.setKeyResolver(userKeyResolver())))
				.uri("lb://USERS"))
				.route(p->p
				.path("/healthtracker/user-body-parameter/**")
				.filters(f->f.rewritePath("/healthtracker/user-body-parameter/(?<segment>.*)","/${segment}")
				.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
				.retry(retryConfig -> retryConfig.setRetries(3)
						.setMethods(HttpMethod.GET)
						.setBackoff(Duration.ofMillis(100),Duration.ofMillis(1000),3,true))
				.requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
						.setKeyResolver(userKeyResolver())))
				.uri("lb://USER-BODY-PARAMETERS"))
				.build();
	}
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
	}
	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	KeyResolver userKeyResolver() {
		return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
				.defaultIfEmpty("anonymous");
	}
}
