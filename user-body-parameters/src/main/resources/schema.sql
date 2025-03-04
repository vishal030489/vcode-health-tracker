CREATE TABLE USER_BODY_PARAMETER(
id int,
mobile varchar(20) NOT NULL
age int NOT NULL,
height int NOT NULL,
weight double NOT NULL,
bmi int NOT NULL,
bmi_remark varchar(10) NOT NULL,
required_bmi int DEFAULT NULL,
health_goal varchar(10) NOT NULL,
PRIMARY KEY (id)
);