package com.example.app.auth;

import com.example.app.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Minimal login controller: verifies credentials via Spring-Security's
 * AuthenticationManager and returns a freshly-minted JWT.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    /* ------------------------------------------------------------- */
    /* POST /auth/login                                              */
    /* ------------------------------------------------------------- */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password()));

        String username = auth.getName();
        // take first authority ("ROLE_ADMIN"), strip prefix:
        String role = auth.getAuthorities().stream()
                          .findFirst().orElseThrow()
                          .getAuthority().replace("ROLE_", "");

        String token = jwtTokenProvider.generateToken(username, role);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    /* ------------------------------------------------------------- */
    /* Simple DTOs (Java 16+ records)                                */
    /* ------------------------------------------------------------- */
    public record LoginRequest(String email, String password) {}
    public record LoginResponse(String token)                  {}
}
