package com.example.app.controller;

import com.example.app.model.FeatureDTO;
import com.example.app.model.UserDTO;
import com.example.app.model.UserRole;
import com.example.app.user.UserEntity;
import com.example.app.user.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/** Simple controller for demo endpoints. */
@RestController
public class FeatureController {

  private final UserService userService;

  public FeatureController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/feature")
  public ResponseEntity<List<FeatureDTO>> featureGet() {
    FeatureDTO sample = new FeatureDTO().id(1L).name("Sample feature");
    return ResponseEntity.ok(List.of(sample));
  }

  @GetMapping("/status")
  public ResponseEntity<String> statusGet() {
    return ResponseEntity.ok("alive");
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> usersGet() {
    List<UserDTO> users =
        userService.findAll().stream()
            .map(
                u ->
                    new UserDTO()
                        .id(u.getId())
                        .role(UserRole.valueOf(u.getRole().name()))
                        .email(u.getEmail()))
            .collect(Collectors.toList());
    return ResponseEntity.ok(users);
  }

  @PostMapping("/users")
  public ResponseEntity<Void> usersPost(@Valid @RequestBody UserDTO userDTO) {
    UserEntity entity =
        new UserEntity(
            null,
            com.example.app.user.UserRole.valueOf(userDTO.getRole().name()),
            userDTO.getEmail(),
            userDTO.getPasswordHash());
    userService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
