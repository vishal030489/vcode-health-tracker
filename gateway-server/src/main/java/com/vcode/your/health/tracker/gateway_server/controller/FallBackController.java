package com.vcode.your.health.tracker.gateway_server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {
	@RequestMapping("/fetchUserData")
	public Mono<String> fetchUserData(){
		return Mono.just("An error occured. Please try after some time or contact support team!!!");
	}
}
