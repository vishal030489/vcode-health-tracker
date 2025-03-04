package com.vcode.your.health.tracker.users.function;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vcode.your.health.tracker.users.service.IUserService;

@Configuration
public class UsersFunction {

	
	private static final Logger log = LoggerFactory.getLogger(UsersFunction.class);

    @Bean
    public Consumer<String> updateCommunication(IUserService userService) {
        return mobileNumber -> {
            log.info("Updating Communication status for the mobile number : " + mobileNumber);
            userService.updateCommunicationStatus(mobileNumber);
        };
    }
	
}
