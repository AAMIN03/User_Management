//package com.springrest.User_Management.services;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoder;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class JwtTokenImpl implements JwtToken {
//
//    @Override
//    public String generateToken(String username){
//        Map<String,Object> claims = new HashMap<>();
//        return createToken(claims,username);
//    }
//
//    private String createToken(Map<String,Object> claims,String username){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
//                .signWith(getSignkey(), SignatureAlgorithm.ES256).compact();
//    }
//
//    private Key getSignkey() {
//        byte[] keyBytes = Decoders.BASE64.decode("25432A462D4A614E645267556B58703273357538782F413F4428472B4B625065");
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//}
