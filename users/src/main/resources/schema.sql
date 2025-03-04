CREATE TABLE USER (
user_id int,
uname varchar(100) NOT NULL,
email varchar(100) NOT NULL,
mobile varchar(10) NOT NULL,
created_by varchar(100) NOT NULL,
created_on date DEFAULT NULL,
updated_by varchar(100) NOT NULL,
updated_on date DEFAULT NULL,
PRIMARY KEY (user_id)
);