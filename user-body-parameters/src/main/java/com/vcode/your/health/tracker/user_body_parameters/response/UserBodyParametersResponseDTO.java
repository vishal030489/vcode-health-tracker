package com.vcode.your.health.tracker.user_body_parameters.response;

import com.vcode.your.health.tracker.user_body_parameters.dto.UserBodyParameterDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBodyParametersResponseDTO {
	
	private String responseCode;
	private String responseMessage;
	private UserBodyParameterDTO userParameterDTO;
	
}
