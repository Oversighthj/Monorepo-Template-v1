package com.example.app.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.app.TemplateApplication;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = TemplateApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("it-auth")
@Transactional
class SecurityEnabledIT {

  @Autowired MockMvc mockMvc;
  @Autowired UserRepository userRepo;
  @Autowired JwtTokenProvider tokenProvider;

  private String adminToken;
  private String guestToken;

  @BeforeEach
  void setup() {
    userRepo.deleteAll();
    UserEntity admin = userRepo.save(new UserEntity(null, UserRole.ADMIN, "admin@test.io", "hash"));
    UserEntity guest = userRepo.save(new UserEntity(null, UserRole.GUEST, "guest@test.io", "hash"));
    adminToken = tokenProvider.generateToken(admin.getEmail(), admin.getRole().name());
    guestToken = tokenProvider.generateToken(guest.getEmail(), guest.getRole().name());
  }

  @Test
  void getUsersWithoutTokenReturns401() throws Exception {
    mockMvc.perform(get("/users")).andExpect(status().isUnauthorized());
  }

  @Test
  void getUsersWithGuestReturns403() throws Exception {
    mockMvc
        .perform(get("/users").header("Authorization", "Bearer " + guestToken))
        .andExpect(status().isForbidden());
  }

  @Test
  void getUsersWithAdminReturns200() throws Exception {
    mockMvc
        .perform(get("/users").header("Authorization", "Bearer " + adminToken))
        .andExpect(status().isOk());
  }
}
