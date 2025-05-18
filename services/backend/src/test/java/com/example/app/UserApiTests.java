package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserApiTests {

  @Autowired private MockMvc mockMvc;

  @Autowired private UserRepository userRepository;

  @Test
  void postAndGetUsers() throws Exception {
    userRepository.deleteAll();
    String body = "{\"role\":\"GUEST\",\"email\":\"new@example.com\",\"passwordHash\":\"x\"}";
    mockMvc
        .perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());

    assertThat(userRepository.findAll()).hasSize(1);

    mockMvc
        .perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value("new@example.com"))
        .andExpect(jsonPath("$[0].passwordHash").doesNotExist());
  }
}
