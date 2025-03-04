package com.vcode.your.health.tracker.email_message.function;


import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vcode.your.health.tracker.email_message.dto.UserNotificationDto;

@Configuration
public class UserNotificationFunction {
	
    private static final Logger log = LoggerFactory.getLogger(UserNotificationFunction.class);

	
    @Bean
    public Function<UserNotificationDto,UserNotificationDto> email() {
        return userNotificationDto -> {
            log.info("Sending email with the details : " +  userNotificationDto.toString());
            return userNotificationDto;
        };
    }

    @Bean
    public Function<UserNotificationDto,String> sms() {
        return userNotificationDto -> {
            log.info("Sending sms with the details : " +  userNotificationDto.toString());
            return userNotificationDto.mobileNumber();
        };
    }
	

}
