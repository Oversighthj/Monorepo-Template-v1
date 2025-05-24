package com.example.app.user;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserEntity create(UserEntity user) {
    user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
    return userRepository.save(user);
  }

  public List<UserEntity> findAll() {
    return userRepository.findAllByOrderByIdAsc();
  }

  public Optional<UserEntity> validateCredentials(String email, String rawPassword) {
    return userRepository
        .findByEmail(email)
        .filter(u -> passwordEncoder.matches(rawPassword, u.getPasswordHash()));
  }
}