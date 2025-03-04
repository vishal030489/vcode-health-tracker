package com.vcode.your.health.tracker.user_body_parameters.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.vcode.your.health.tracker.user_body_parameters.dto.UserBodyParameterDTO;
import com.vcode.your.health.tracker.user_body_parameters.entiry.UserBodyParameter;
import com.vcode.your.health.tracker.user_body_parameters.exception.ResourceAlreadyExistsException;
import com.vcode.your.health.tracker.user_body_parameters.exception.ResourceNotFoundException;
import com.vcode.your.health.tracker.user_body_parameters.mapper.UserBodyParameterMapper;
import com.vcode.your.health.tracker.user_body_parameters.repository.UserBodyParameterRepository;
import com.vcode.your.health.tracker.user_body_parameters.utility.BMICalculator;
import com.vcode.your.health.tracker.user_body_parameters.utility.BMIData;

import jakarta.validation.Valid;
@Service
public class IUserBodyParameterServiceImpl implements IUserBodyParameterService {

	public IUserBodyParameterServiceImpl(UserBodyParameterRepository userBodyParameterRepository) {
		this.userBodyParameterRepository = userBodyParameterRepository;
	} 
	
	private UserBodyParameterRepository userBodyParameterRepository;
	
	@Override
	public void save(@Valid UserBodyParameterDTO userBodyParameterDTO) {
		UserBodyParameter userBodyParameter = 
				UserBodyParameterMapper.mapToEntity(userBodyParameterDTO, new UserBodyParameter());
		
		Optional<UserBodyParameter> userBodyParameterDb = userBodyParameterRepository.findByMobile(userBodyParameterDTO.getMobile());
		
		if(userBodyParameterDb.isPresent()) {
			throw new ResourceAlreadyExistsException("User Body Parameters for user with mobile number "+userBodyParameterDTO.getMobile()+" is already exists.");
		}
		BMIData bmiData=BMICalculator.CalculateBMI(userBodyParameter.getWeight(), userBodyParameter.getHeight());
		userBodyParameter.setBmi(bmiData.getBmi());
		userBodyParameter.setBmiRemark(bmiData.getBmiRemark());
		userBodyParameterRepository.save(userBodyParameter);
	}

	@Override
	public boolean update(@Valid UserBodyParameterDTO userBodyParameterDTO) {
		boolean isUpdated = false;
		UserBodyParameter userBodyParameter = userBodyParameterRepository.findByMobile(userBodyParameterDTO.getMobile()).orElseThrow(
                 () -> new ResourceNotFoundException("UserBodyParameter", "Mobile", userBodyParameterDTO.getMobile().toString())
         );
		userBodyParameterRepository.save(userBodyParameter);
		isUpdated = true;
	    return isUpdated;
	}

	@Override
	public UserBodyParameterDTO findByMobile(String mobile) {
		UserBodyParameter userBodyParameter = userBodyParameterRepository.findByMobile(mobile).orElseThrow(
                () -> new ResourceNotFoundException("UserBodyParameter", "Mobile", mobile)
        );
		UserBodyParameterDTO userParameterDTO = UserBodyParameterMapper.mapToDTO(userBodyParameter, new UserBodyParameterDTO());
		return userParameterDTO;
	}

}
