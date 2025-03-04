package com.vcode.your.health.tracker.user_body_parameters.mapper;

import com.vcode.your.health.tracker.user_body_parameters.dto.UserBodyParameterDTO;
import com.vcode.your.health.tracker.user_body_parameters.entiry.UserBodyParameter;

public class UserBodyParameterMapper {
	
		public static UserBodyParameter mapToEntity(UserBodyParameterDTO dto,UserBodyParameter ubp) {
			ubp.setMobile(dto.getMobile());
			ubp.setAge(dto.getAge());
			ubp.setHeight(dto.getHeight());
			ubp.setWeight(dto.getWeight());
			ubp.setBmi(dto.getBmi());
			ubp.setBmiRemark(dto.getBmiRemark());
			//ubp.setRequiredBmi(dto.getRequiredBmi());
			ubp.setHealthGoal(dto.getHealthGoal());
			return ubp;
		}
		
		public static UserBodyParameterDTO mapToDTO(UserBodyParameter ubp,UserBodyParameterDTO dto) {
			dto.setMobile(ubp.getMobile());
			dto.setAge(ubp.getAge());
			dto.setHeight(ubp.getHeight());
			dto.setWeight(ubp.getWeight());
			dto.setBmi(ubp.getBmi());
			dto.setBmiRemark(ubp.getBmiRemark());
			//dto.setRequiredBmi(ubp.getRequiredBmi());
			dto.setHealthGoal(ubp.getHealthGoal());
			return dto;
		}
}
