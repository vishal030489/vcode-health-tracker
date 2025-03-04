package com.vcode.your.health.tracker.user_body_parameters.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserBodyParameterDTO {
	
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobile;
	
	@Min(value=1, message="age: positive number, min 1 is required")
	@Max(value=150, message="age: positive number, max 150 is required")
	private int age;
	
	@Min(value=1, message="age: positive number, min 1 is required")
	@Max(value=500, message="age: positive number, max 500 is required")
	private int height;
	
	@Min(value=1, message="age: positive number, min 1 is required")
	@Max(value=150, message="age: positive number, max 500 is required")
	private double weight;
	
	private double bmi;
	
	private String bmiRemark;

	@NotEmpty
	private String healthGoal;

}
