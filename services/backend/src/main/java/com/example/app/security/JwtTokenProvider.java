package com.example.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms:3600000}")
    private long expirationMs;

    private Key key;

    @PostConstruct
    void init() {
        // secret must be 256‑bit (32‑byte) for HS256
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Authentication auth) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);
        String authorities = auth.getAuthorities()
                                 .stream()
                                 .map(GrantedAuthority::getAuthority)
                                 .collect(Collectors.joining(","));
        return Jwts.builder()
                   .setSubject(auth.getName())
                   .claim("role", authorities) // single role in our model
                   .setIssuedAt(now)
                   .setExpiration(expiry)
                   .signWith(key, SignatureAlgorithm.HS256)
                   .compact();
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(token);
    }
}
