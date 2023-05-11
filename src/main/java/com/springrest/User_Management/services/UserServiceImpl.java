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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

import com.springrest.User_Management.dao.UserDao;
import com.springrest.User_Management.entities.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	//List <User> list;
	
	public UserServiceImpl() {
			
//			list=new ArrayList<>();
//			list.add(new User("aab","Aamin","Chaudhari",7046757423L,"aaminchaudhari@gmail.com","hcgjc","hgjsh"));
//			list.add(new User("abb","Aamin","Chaudhari",7046757423L,"chaudhari@gmail.com","hcgjc","hgjsh"));
//		
		}

	
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
//		User x=null;
//		for(User user:list) {
//			if(user.getMobileNo() == mobileNo) {
//				x=user;
//				break;
//			}
////			String t=user.getEmail();
////			System.out.println(t);
//			//x.emailid = t;
//		}
//		return x;
//		Optional<User> userOptional = userDao.findById(mobileNo);
//        return userOptional.orElse(null);
		//return userDao.findById(id).orElseThrow(()->new NoSuchElementException("User  not found"));

		Optional<User> existingUser = userDao.findById(id);
//	    if (existingUser.isEmpty()) {
//	    	return new ResponseEntity<>("No user exist with requested id.",HttpStatus.NOT_FOUND);
//	    }
	    User user = existingUser.get();
        return new ResponseEntity<>(user, HttpStatus.OK);

//		Optional<User> userOptional = userDao.findById(mobileNo);
//        if (userOptional.isPresent()) {
//            return userOptional.get();
//        } else {
//            // handle the case where the user with the given ID was not found
//        }

//		User user = userDao.findByMobileNumber(mobileNo);
////		if (user != null) {
////		    // Do something with the user object
////			//return
////		}
//		return user;
//		} else {
//		    // Handle the case where the user with the given mobile number was not found
//
//		}
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
	    return new ResponseEntity<>(HttpStatus.CREATED);

//		userDao.save(user);
//		return user;
	}

	//updating user data
	@Override
	public ResponseEntity<?> updateUser(String id,User userToBeUpdated) {
		// TODO Auto-generated method stub
//		list.forEach(element->{
//			if(element.getMobileNo()==user.getMobileNo()) {
//				element.setFirstname(user.getFirstname());
//				element.setLastname(user.getLastname());
//				element.setAddress1(user.getAddress1());
//				element.setAddress2(user.getAddress2());
//			}
//		});
		Optional<User> existingUser = userDao.findById(id);
	    if (existingUser.isPresent()) {
	        User user = existingUser.get();
	        user.setAddress1(userToBeUpdated.getAddress1());
	        user.setAddress2(userToBeUpdated.getAddress2());
//	        if(userDao.existsByEmail(userToBeUpdated.getEmail())) {
//	        	return new ResponseEntity<>("User already exists with this email.",HttpStatus.BAD_REQUEST);
//	        }else {
	        user.setEmail(userToBeUpdated.getEmail());
	       // }

//	        if(userDao.existsByMobileNo(userToBeUpdated.getMobileNo())) {
//	        	return new ResponseEntity<>("User already exists with given Mobile number.",HttpStatus.BAD_REQUEST);
//	        }else {
	        user.setMobileNo(userToBeUpdated.getMobileNo());
	        user.setFirstname(userToBeUpdated.getFirstname());
	        user.setLastname(userToBeUpdated.getLastname());
	        userDao.save(user);
	        return new ResponseEntity<>("User data updated successfully.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("No user found with requested id.",HttpStatus.NOT_FOUND);
	    }

	}

	// deleting user
	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		//list=this.list.stream().filter(element->element.getMobileNo()!=mobileNo).collect(Collectors.toList());
		User entity = userDao.getReferenceById(id);
		userDao.delete(entity);
	}

}
