package com.example.app.service;

import java.util.ArrayList;
import java.util.List;
import org.openapitools.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final List<UserDTO> users = new ArrayList<>();
  private long nextId = 1L;

  public List<UserDTO> findAll() {
    return new ArrayList<>(users);
  }

  public UserDTO create(UserDTO dto) {
    UserDTO created = new UserDTO().id(nextId++).name(dto.getName());
    users.add(created);
    return created;
  }

  public void clear() {
    users.clear();
    nextId = 1L;
  }
}
