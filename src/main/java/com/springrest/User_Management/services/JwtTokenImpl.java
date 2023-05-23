package com.springrest.User_Management.services;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenImpl implements JwtToken {

    @Override
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    private String createToken(Map<String,Object> claims,String username){
//        return Jwts.builder()
//                .setClaims(claims);
        return null;
    }
}
