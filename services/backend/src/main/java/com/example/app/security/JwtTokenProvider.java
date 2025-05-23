package com.example.app.security;

import com.example.app.user.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms:3600000}")
    private long expirationMs;

    private Key signingKey;

    @PostConstruct
    public void init() {
        signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // إنشاء توكن جديد مع الدور ووقت الانتهاء
    public String generateToken(UserEntity user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("role", user.getRole().name())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expirationMs)))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // التحقق من صلاحية التوكن
    public boolean isValid(String token) {
        try {
            return parse(token).getExpiration().after(new Date());
        } catch (Exception ex) {
            return false;
        }
    }

    // بناء Authentication من التوكن
    public Authentication getAuthentication(String token) {
        Claims c = parse(token);
        return new UsernamePasswordAuthenticationToken(
                c.getSubject(),
                null,
                Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + c.get("role", String.class))
                )
        );
    }

    // -------------------------------------
    //   دالة داخلية لتحليل الــــ JWT
    // -------------------------------------
    private Claims parse(String token) {
        return Jwts.parserBuilder()          // نستخدم builder
                   .setSigningKey(signingKey)
                   .build()                    // ثم نبني الـ JwtParser
                   .parseClaimsJws(token)      // وأخيرًا نحلل التوكن
                   .getBody();
    }
}
