package com.vcode.your.health.tracker.users.feign.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.vcode.your.health.tracker.users.dto.UserBodyParameterDTO;

@Component
public class UserBodyParameterFeignClientFallBack implements UserBodyParameterFeignClient {

	@Override
	public ResponseEntity<UserBodyParameterDTO> fetchUserBodyParameters(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
