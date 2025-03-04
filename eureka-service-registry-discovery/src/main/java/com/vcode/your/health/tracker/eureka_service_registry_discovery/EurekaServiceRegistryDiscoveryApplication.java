package com.vcode.your.health.tracker.eureka_service_registry_discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceRegistryDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceRegistryDiscoveryApplication.class, args);
	}

}
