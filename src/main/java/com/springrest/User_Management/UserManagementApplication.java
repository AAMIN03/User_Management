package com.springrest.User_Management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages={"com.springrest.User_Management", "com.springrest.User_Management.controller","com.springrest.User_Management.services","com.springrest.User_Management.dao","com.springrest.User_Management.entities"} )
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

}
