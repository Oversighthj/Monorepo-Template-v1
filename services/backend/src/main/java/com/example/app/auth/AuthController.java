package com.example.app.auth;

import com.example.app.security.JwtTokenProvider;
import com.example.app.user.UserEntity;
import com.example.app.user.UserService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  private final UserService userService;
  private final JwtTokenProvider jwtTokenProvider;

  public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider) {
    this.userService = userService;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @PostMapping("/auth/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
    String email = body.get("email");
    String password = body.get("password");
    return userService
        .validateCredentials(email, password)
        .map(UserEntity::getEmail)
        .map(jwtTokenProvider::generateToken)
        .map(token -> ResponseEntity.ok(Map.of("token", token)))
        .orElseGet(() -> ResponseEntity.status(401).build());
  }
}