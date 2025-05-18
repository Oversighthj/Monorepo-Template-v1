package com.example.app.user;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity save(UserEntity user) {
    return userRepository.save(user);
  }

  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }
}
