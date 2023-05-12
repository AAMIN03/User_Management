package com.springrest.User_Management.controller;

import java.util.List;
import java.util.Optional;

import com.springrest.User_Management.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.User_Management.entities.User;
import com.springrest.User_Management.services.UserService;

@RestController
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
			return this.userService.addUser(user);
		}

		//Updating user
		@PutMapping("/users/{id}")
		public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User user) {
			return this.userService.updateUser(id,user);
		}

		//Deleting user
		@DeleteMapping("/users/{id}")
		public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id){
			try {
				this.userService.deleteUser(id);
				return new  ResponseEntity<>(HttpStatus.OK);
			}catch(Exception e){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}

}
