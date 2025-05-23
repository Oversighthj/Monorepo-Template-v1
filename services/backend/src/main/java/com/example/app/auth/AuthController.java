package com.example.app.auth;

import com.example.app.security.JwtTokenProvider;
import com.example.app.user.UserEntity;
import com.example.app.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    public record LoginDTO(String email, String password) {}
    public record TokenDTO(String token, String role) {}

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO dto) {
        UserEntity user = userService.validateCredentials(dto.email(), dto.password());
        String token = tokenProvider.generateToken(user);
        return ResponseEntity.ok(new TokenDTO(token, user.getRole().name()));
    }
}
