package com.springrest.User_Management.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.entities.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	//getting all the users
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	//getting single user by its id
	@Override
	public ResponseEntity<?> getUser(String id) {
		// TODO Auto-generated method stub

		Optional<User> existingUser = userDao.findById(id);
	    if (existingUser.isEmpty()) {
	    	return new ResponseEntity<>("No user exist with requested id.",HttpStatus.NOT_FOUND);
	    }
	    User user = existingUser.get();
        return new ResponseEntity<>(user, HttpStatus.OK);
	}

	//adding user in db
	@Override
	public ResponseEntity<?> addUser(@RequestBody @NotNull User user) {
		// TODO Auto-generated method stub
		//list.add(user);

		if (userDao.existsByEmail(user.getEmail())) {
	        return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
	    }
	    if (userDao.existsById(user.getid())) {
	        return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
	    }
	    if (userDao.existsByMobileNo(user.getMobileNo())) {
	        return new ResponseEntity<>("User already exists.", HttpStatus.BAD_REQUEST);
	    }



	    userDao.save(user);
	    String message = user + "  added successfully.";
	    return new ResponseEntity<>(message,HttpStatus.CREATED);

//		userDao.save(user);
//		return user;
	}

	//updating user data
	@Override
	public ResponseEntity<?> updateUser(String id,User userToBeUpdated) {
		// TODO Auto-generated method stub
		Optional<User> existingUser = userDao.findById(id);
	    if (existingUser.isPresent()) {
	        User user = existingUser.get();
	        user.setAddress1(userToBeUpdated.getAddress1());
	        user.setAddress2(userToBeUpdated.getAddress2());
	        user.setEmail(userToBeUpdated.getEmail());
	        user.setMobileNo(userToBeUpdated.getMobileNo());
	        user.setFirstname(userToBeUpdated.getFirstname());
	        user.setLastname(userToBeUpdated.getLastname());
	        userDao.save(user);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	}

	// deleting user
	@Override
	public ResponseEntity<?> deleteUser(String id) {
		User entity = userDao.getReferenceById(id);
		if(!userDao.existsById(entity.getid())) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userDao.delete(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
