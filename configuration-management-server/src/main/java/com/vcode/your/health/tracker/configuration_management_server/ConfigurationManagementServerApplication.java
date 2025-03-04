package com.vcode.your.health.tracker.configuration_management_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigurationManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationManagementServerApplication.class, args);
	}

}
