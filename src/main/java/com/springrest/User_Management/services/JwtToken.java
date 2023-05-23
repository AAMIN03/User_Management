package com.springrest.User_Management.services;

import org.springframework.stereotype.Service;

@Service
public interface JwtToken {
    String generateToken(String username);
}
