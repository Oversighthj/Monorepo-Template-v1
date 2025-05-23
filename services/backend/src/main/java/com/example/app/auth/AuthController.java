package com.example.app.auth;

import com.example.app.security.JwtTokenProvider;
import com.example.app.user.UserEntity;
import com.example.app.user.UserService;
import com.example.app.auth.LoginRequest;
import com.example.app.auth.LoginResponse;
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
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return userService
        .validateCredentials(request.email(), request.password())
        .map(UserEntity::getEmail)
        .map(jwtTokenProvider::generateToken)
        .map(token -> ResponseEntity.ok(new LoginResponse(token)))
        .orElseGet(() -> ResponseEntity.status(401).build());
  }
}
