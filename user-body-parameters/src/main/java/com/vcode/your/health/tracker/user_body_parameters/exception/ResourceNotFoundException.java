package com.vcode.your.health.tracker.user_body_parameters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private String resource;
	private String paramaterName;
	private String parameterValue;
	
	public ResourceNotFoundException(String resource,String paramaterName,String parameterValue) {
        super(String.format("%s not found with the given input data %s : '%s'", resource, paramaterName, parameterValue));
	}
	

}
