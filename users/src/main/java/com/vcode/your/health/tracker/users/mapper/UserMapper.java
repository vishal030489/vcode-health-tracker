package com.vcode.your.health.tracker.users.mapper;

import com.vcode.your.health.tracker.users.dto.UserDTO;
import com.vcode.your.health.tracker.users.entity.User;

public class UserMapper {
	
	public static UserDTO mapTOUserDto(User user, UserDTO newUserDto) {		
		newUserDto.setUname(user.getUname());
		newUserDto.setMobile(user.getMobile());
		newUserDto.setEmail(user.getEmail());
		return newUserDto;
	}
	
	public static User mapTOUser(UserDTO userDTO,User newUser) {
		newUser.setUname(userDTO.getUname());
		newUser.setMobile(userDTO.getMobile());
		newUser.setEmail(userDTO.getEmail());
		return newUser;
	}
}
