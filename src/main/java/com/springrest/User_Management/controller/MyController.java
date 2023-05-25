package com.springrest.User_Management.controller;

import java.util.List;
import java.util.Optional;

import com.springrest.User_Management.Dto.AuthRequest;
import com.springrest.User_Management.services.UserServiceImpl;
import com.springrest.User_Management.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.services.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;


@RestController
@Validated
public class MyController {
	
	@Autowired
	private UserService userService;

//	@Autowired
//	private UserServiceImpl userServiceImpl;
	
	@GetMapping("/home")
	public String home() {
		return "Welcome to the User Management portal.";
	}
	
	//Get the Users
	@GetMapping("/users")
	public List<User> getUsers(){
		return this.userService.getUsers();
	}

	//Get the user
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getUser(@PathVariable String id){
		return this.userService.getUser(id);
	}

	//Adding user
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		String regexp = "\\d{10}";
		if(user.getMobileNo().matches(regexp)){
			return this.userService.addUser(user);
		}else {
			return new ResponseEntity<>("Invalid mobile no.", HttpStatus.BAD_REQUEST);
		}
	}

	//Updating user
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id,@Valid @RequestBody User user) {
		return this.userService.updateUser(id,user);
	}

	//Deleting user
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id){
		return this.userService.deleteUser(id);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
		return null;
	}

}
