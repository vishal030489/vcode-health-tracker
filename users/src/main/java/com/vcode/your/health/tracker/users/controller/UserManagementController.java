package com.vcode.your.health.tracker.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vcode.your.health.tracker.users.constants.UserConstants;
import com.vcode.your.health.tracker.users.dto.ResponseDTO;
import com.vcode.your.health.tracker.users.dto.UserBodyParameterDTO;
import com.vcode.your.health.tracker.users.dto.UserDTO;
import com.vcode.your.health.tracker.users.record.UsersContactInfoDto;
import com.vcode.your.health.tracker.users.service.IUserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UserManagementController {
	
	public IUserService userService;
	
	public UserManagementController(IUserService userService) {
		this.userService = userService;
	}
	
	 @Autowired
	 private UsersContactInfoDto usersContactInfoDto;

	@GetMapping("/test")
	public String test() {
		return "Test Api";
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		userService.createUser(userDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(UserConstants.SAVINGS,UserConstants.MESSAGE_201,null,null));
	}
	
	@Retry(name="fetchUser",fallbackMethod="fetchUserFallback")
	@GetMapping("/fetch")
	public ResponseEntity<ResponseDTO> fetchUser(@RequestParam("mobileNumber") String mobileNumber){
		UserDTO userDTO=userService.fetchUser(mobileNumber);
		System.out.println("retry with user service");
		throw new NullPointerException();

		//return ResponseEntity.status(HttpStatus.OK)
				//.body(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,userDTO,null));
	}
	
	public ResponseEntity<ResponseDTO> fetchUserFallback(@RequestParam("mobileNumber") String mobileNumber,Throwable throwable){
		UserDTO userDTO =new UserDTO();
		userDTO.setMobile("1234567891");
		userDTO.setEmail("test@vishal");
		userDTO.setUname("Test User");
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(UserConstants.STATUS_200,UserConstants.MESSAGE_200,userDTO,null));
	}
			
		
	
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateUser(@Valid @RequestBody UserDTO userDTO){
		boolean isUpdated = userService.updateUser(userDTO);
		if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(UserConstants.STATUS_200, UserConstants.MESSAGE_200,null,null));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(UserConstants.STATUS_417, UserConstants.MESSAGE_417_UPDATE,null,null));
        }
	}
	@GetMapping("/fetchUserData")
	public ResponseEntity<UserBodyParameterDTO> fetchUserBodyParameters(@RequestParam(name="mobileNumber") String mobileNumber){
		UserBodyParameterDTO userDTO=userService.fetchUserBodyParameters(mobileNumber);
		System.out.println("Retrying for service.....");
		return ResponseEntity.status(HttpStatus.OK)
				.body(userDTO);
	}
	
	@RateLimiter(name="getContactDetails",fallbackMethod = "getContactDetailsFallback")
	@GetMapping("/contact-info")
	public ResponseEntity<UsersContactInfoDto> getContactDetails(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(usersContactInfoDto);
	}
	
	public ResponseEntity<UsersContactInfoDto> getContactDetailsFallback(Throwable throwable){
		UsersContactInfoDto userContactInfoDto1 = new UsersContactInfoDto();
		return ResponseEntity.status(HttpStatus.OK)
				.body(userContactInfoDto1);
	}
	

}
