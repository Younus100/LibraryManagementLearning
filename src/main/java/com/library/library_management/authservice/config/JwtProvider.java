package com.library.library_management.authservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {
    SecretKey secretKey = JWTConstants.SECRET_KEY;

    public String generateToken(Authentication auth){
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+846000000))
                .claim("username",auth.getName())
                .signWith(SignatureAlgorithm.HS256, JWTConstants.SECRET_KEY).compact();

        return jwt;
    }
    public String getemailFromToken(String jwt){
        jwt=jwt.substring(7);
        Claims claims = Jwts.parser().setSigningKey(JWTConstants.SECRET_KEY).parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }

}


