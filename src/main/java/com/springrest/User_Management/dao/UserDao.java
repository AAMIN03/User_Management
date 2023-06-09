package com.springrest.User_Management.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.User_Management.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String>{
	//Optional<User> findById(Long mobileNo);
	//User findByMobileNumber(long mobileNo);
	
	boolean existsByEmail(String email);
    boolean existsById(String id);
    boolean existsByMobileNo(String mobileNo);

    Optional<User> findByFirstname(String username);
}
