package com.vcode.your.health.tracker.user_body_parameters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserBodyParametersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserBodyParametersApplication.class, args);
	}

}
