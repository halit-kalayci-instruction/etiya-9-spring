package com.etiya.academy.core.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService
{
  @Value("${jwt.expiration}")
  private Long EXPIRATION;
  @Value("${jwt.secret_key}")
  private String SECRET_KEY;


  public String generateToken(String username)
  {
    return Jwts.builder()
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .subject(username)
            .signWith(getSignKey())
            .compact();
  }

  private Key getSignKey()
  {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}

// Spring Security + JWT ?

