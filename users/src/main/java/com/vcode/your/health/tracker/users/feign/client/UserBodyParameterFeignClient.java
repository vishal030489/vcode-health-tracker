package com.vcode.your.health.tracker.users.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcode.your.health.tracker.users.dto.UserBodyParameterDTO;

@FeignClient(name="user-body-parameters",fallback=UserBodyParameterFeignClientFallBack.class)
public interface UserBodyParameterFeignClient {
	 @GetMapping(value = "/bodyparameterapi/fetch",consumes = "application/json")
	    public ResponseEntity<UserBodyParameterDTO> fetchUserBodyParameters(@RequestParam(name="mobileNumber") String mobileNumber);
}
