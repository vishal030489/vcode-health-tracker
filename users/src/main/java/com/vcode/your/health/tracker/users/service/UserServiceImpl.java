package com.vcode.your.health.tracker.users.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.vcode.your.health.tracker.users.dto.UserBodyParameterDTO;
import com.vcode.your.health.tracker.users.dto.UserDTO;
import com.vcode.your.health.tracker.users.dto.UserNotificationDto;
import com.vcode.your.health.tracker.users.entity.User;
import com.vcode.your.health.tracker.users.exception.ResourceNotFoundException;
import com.vcode.your.health.tracker.users.exception.UserAlreadyExistsException;
import com.vcode.your.health.tracker.users.feign.client.UserBodyParameterFeignClient;
import com.vcode.your.health.tracker.users.mapper.UserMapper;
import com.vcode.your.health.tracker.users.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

	private UserRepository userRepository;

	private UserBodyParameterFeignClient userBodyParameterFeignClient;

	private final StreamBridge streamBridge;

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void createUser(UserDTO userDTO) {

		Optional<User> user = userRepository.findByMobile(userDTO.getMobile());

		if (user.isPresent()) {
			throw new UserAlreadyExistsException(
					"User with mobile number: " + userDTO.getMobile() + " is already registered.");
		}

		User mappedUser = UserMapper.mapTOUser(userDTO, new User());
		User savedUser = userRepository.save(mappedUser);
		sendCommunication(savedUser);
	}

	private void sendCommunication(User user) {
		var usrsMsgDto = new UserNotificationDto(user.getUname(), user.getEmail(), user.getMobile());
		log.info("Sending Communication request for the details: {}", usrsMsgDto);
		var result = streamBridge.send("sendCommunication-out-0", usrsMsgDto);
		log.info("Is the Communication request successfully triggered ? : {}", result);
	}

	@Override
	public UserDTO fetchUser(String mobile) {
		UserDTO userDTO = null;
		Optional<User> user = userRepository.findByMobile(mobile);
		if (user.isPresent()) {
			userDTO = UserMapper.mapTOUserDto(user.get(), new UserDTO());
		} else {
			throw new ResourceNotFoundException("User", "User Mobile", userDTO.getMobile());
		}
		return userDTO;
	}

	@Override
	public boolean updateUser(@Valid UserDTO userDTO) {
		boolean isUpdate = false;
		User user = userRepository.findByMobile(userDTO.getMobile())
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Mobile", userDTO.getMobile()));
		User updatedUser = UserMapper.mapTOUser(userDTO, user);
		userRepository.save(updatedUser);
		isUpdate = true;
		return isUpdate;
	}

	@Override
	public UserBodyParameterDTO fetchUserBodyParameters(String mobile) {

		ResponseEntity<UserBodyParameterDTO> userBodyParameterDto = userBodyParameterFeignClient
				.fetchUserBodyParameters(mobile);
		if (null != userBodyParameterDto) {
			return userBodyParameterDto.getBody();
		} else {
			throw new ResourceNotFoundException("UserBodyParameterDTO", "mobile", mobile);
		}
	}

	@Override
	public boolean updateCommunicationStatus(String mobileNumber) {
        boolean isUpdated = false;
        if(mobileNumber !=null ){
            User user = userRepository.findByMobile(mobileNumber).orElseThrow(
                    () -> new ResourceNotFoundException("User", "MobileNumber", mobileNumber)
            );
            user.setCommunicationSw(true);
            userRepository.save(user);
            isUpdated = true;
        }
        return  isUpdated;
    }
}
