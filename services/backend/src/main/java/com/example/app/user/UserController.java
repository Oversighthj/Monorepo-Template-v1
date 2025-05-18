package com.example.app.user;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.model.UserDTO;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity<List<UserDTO>> getUsers() {
    List<UserDTO> users =
        userService.findAll().stream().map(UserController::toDtoWithoutPassword).toList();
    return ResponseEntity.ok(users);
  }

  @PostMapping("/users")
  public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto) {
    UserEntity entity = new UserEntity(null, dto.getRole(), dto.getEmail(), dto.getPasswordHash());
    UserEntity saved = userService.save(entity);
    UserDTO response = toDtoWithoutPassword(saved);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  private static UserDTO toDtoWithoutPassword(UserEntity entity) {
    UserDTO dto = new UserDTO();
    dto.setId(entity.getId());
    dto.setRole(entity.getRole());
    dto.setEmail(entity.getEmail());
    return dto;
  }
}
