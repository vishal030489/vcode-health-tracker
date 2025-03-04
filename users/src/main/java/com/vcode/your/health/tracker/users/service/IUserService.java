package com.vcode.your.health.tracker.users.service;

import com.vcode.your.health.tracker.users.dto.UserBodyParameterDTO;
import com.vcode.your.health.tracker.users.dto.UserDTO;

import jakarta.validation.Valid;


public interface IUserService {
	public void createUser(UserDTO userDTO);

	public UserDTO fetchUser(String mobile);

	public boolean updateUser(@Valid UserDTO userDTO);

	public UserBodyParameterDTO fetchUserBodyParameters(String mobile);

	public boolean updateCommunicationStatus(String mobileNumber);
}
