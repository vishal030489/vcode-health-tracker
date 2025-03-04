package com.vcode.your.health.tracker.user_body_parameters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vcode.your.health.tracker.user_body_parameters.constants.UserBodyParameterConstants;
import com.vcode.your.health.tracker.user_body_parameters.dto.UserBodyParameterDTO;
import com.vcode.your.health.tracker.user_body_parameters.response.UserBodyParametersResponseDTO;
import com.vcode.your.health.tracker.user_body_parameters.service.IUserBodyParameterService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(path="/bodyparameterapi")
@Validated
public class UserBodyParametersManagementController {
	
	private IUserBodyParameterService  iUserBodyParameterService;
	
	public UserBodyParametersManagementController(IUserBodyParameterService  iUserBodyParameterService) {
		this.iUserBodyParameterService = iUserBodyParameterService;
	}
	
	@PostMapping("/save")
	public ResponseEntity<UserBodyParametersResponseDTO> saveUserBodyParameters(@Valid @RequestBody UserBodyParameterDTO userBodyParameterDTO){
		iUserBodyParameterService.save(userBodyParameterDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserBodyParametersResponseDTO(UserBodyParameterConstants.STATUS_201,
				UserBodyParameterConstants.MESSAGE_201,null));
	}
	
	@PostMapping("/update")
	public ResponseEntity<UserBodyParametersResponseDTO> updateUserBodyParameters(@Valid @RequestBody UserBodyParameterDTO userBodyParameterDTO){
		boolean isUpdated=iUserBodyParameterService.update(userBodyParameterDTO);
		if(isUpdated) {
		return ResponseEntity.status(HttpStatus.OK).body(new UserBodyParametersResponseDTO(UserBodyParameterConstants.STATUS_200,
				UserBodyParameterConstants.MESSAGE_200,null));
		}else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new UserBodyParametersResponseDTO(UserBodyParameterConstants.STATUS_417,
					UserBodyParameterConstants.MESSAGE_417_UPDATE,null));
		}
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<UserBodyParameterDTO> fetchUserBodyParameters(@RequestParam(name="mobileNumber") String mobileNumber){
		System.out.println("Retrying for service.....");
		UserBodyParameterDTO userBodyParameterDTO = iUserBodyParameterService.findByMobile(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(userBodyParameterDTO);
	}
}
