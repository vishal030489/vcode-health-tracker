package com.vcode.your.health.tracker.user_body_parameters.service;

import com.vcode.your.health.tracker.user_body_parameters.dto.UserBodyParameterDTO;

import jakarta.validation.Valid;

public interface IUserBodyParameterService {

	void save(@Valid UserBodyParameterDTO userBodyParameterDTO);

	boolean update(@Valid UserBodyParameterDTO userBodyParameterDTO);

	UserBodyParameterDTO findByMobile(String mobile);

}
