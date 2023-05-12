package com.springrest.User_Management.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.springrest.User_Management.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UserService {
	
	public List<User> getUsers();
	public ResponseEntity<?> getUser(String id);
	public ResponseEntity<?> addUser(User user);
	public ResponseEntity<?> updateUser(String id,User user);
	public void deleteUser(String id);
}
