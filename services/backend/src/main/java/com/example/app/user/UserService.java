package com.example.app.user;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity create(UserEntity user) {
    return userRepository.save(user);
  }

  public List<UserEntity> findAll() {
    return userRepository.findAllByOrderByIdAsc();
  }

  public UserEntity findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }
}
