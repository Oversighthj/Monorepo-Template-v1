package com.example.app.security;

import com.example.app.TemplateApplication;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests with real SecurityConfig enabled.
 * Profile "it-auth" activates JWT and uses H2 + Flyway migrations.
 */
@SpringBootTest(
        classes = TemplateApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "security.enabled=true",
                "spring.profiles.include=it-auth"
        })
@ActiveProfiles("it-auth")
@Transactional
class SecurityEnabledIT {

    @LocalServerPort
    private int port;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    private String adminJwt;
    private String guestJwt;

    @BeforeEach
    void setUpUsers() {
        // ----- Admin ----------------------------------------------------
        UserEntity admin = new UserEntity();
        admin.setEmail("admin@example.com");
        admin.setPasswordHash("password");          // لا يهم التشفير في هذا الاختبار
        admin.setRole(UserRole.ADMIN);
        userRepository.save(admin);

        // ----- Guest ----------------------------------------------------
        UserEntity guest = new UserEntity();
        guest.setEmail("guest@example.com");
        guest.setPasswordHash("password");
        guest.setRole(UserRole.GUEST);
        userRepository.save(guest);

        // ----- JWTs -----------------------------------------------------
        adminJwt = tokenProvider.generateToken(admin.getEmail(), admin.getRole().name());
        guestJwt = tokenProvider.generateToken(guest.getEmail(), guest.getRole().name());
    } 
    /* ---------- helpers ------------------------------------------------- */

    private ResponseEntity<String> get(String path, String jwt) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        if (jwt != null) headers.setBearerAuth(jwt);
        return rt.exchange("http://localhost:" + port + path,
                           HttpMethod.GET,
                           new HttpEntity<>(headers),
                           String.class);
    }

    /* ---------- tests --------------------------------------------------- */

    @Test
    void request_without_token_returns_401() {
        ResponseEntity<String> res = get("/users", null);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void guest_token_on_admin_endpoint_returns_403() {
        ResponseEntity<String> res = get("/properties", guestJwt);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void admin_token_on_admin_endpoint_returns_200() {
        ResponseEntity<String> res = get("/properties", adminJwt);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
