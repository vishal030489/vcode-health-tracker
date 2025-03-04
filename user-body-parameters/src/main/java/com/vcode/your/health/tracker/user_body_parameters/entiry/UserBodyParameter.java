package com.vcode.your.health.tracker.user_body_parameters.entiry;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter 
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserBodyParameter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String mobile;
	
	@Column
	private int age;
	
	@Column
	private int height;
	
	@Column
	private double weight;
	
	@Column
	private double bmi;
	
	@Column(name="bmi_remark")
	private String bmiRemark;
	
	@Column(name="required_bmi")
	private int requiredBmi;
	
	@Column(name="health_goal")
	private String healthGoal;
	
}
