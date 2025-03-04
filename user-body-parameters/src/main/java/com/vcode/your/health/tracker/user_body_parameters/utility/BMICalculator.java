package com.vcode.your.health.tracker.user_body_parameters.utility;

public class BMICalculator 
{
	
 public static BMIData CalculateBMI(double weight, double height) 
  {
	 BMIData bmiData =new BMIData();
    
    double BMI = ( 100 * 100 * weight ) / ( height * height ); 
    // multiplying 100 * 100 to convert cm to m
	bmiData.setBmi(BMI);

    String remark=null;   
    if(BMI < 18.5)
    	bmiData.setBmiRemark("You are underweight!");
    else if(BMI < 25)
	bmiData.setBmiRemark("You are normal!");

    else if(BMI < 30)
	bmiData.setBmiRemark("You are overweight!");

    else
	bmiData.setBmiRemark("You are obese!");

	return bmiData;
  } 
}