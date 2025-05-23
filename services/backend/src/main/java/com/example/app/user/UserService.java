package com.example.service;

import com.example.model.UserEntity;
import com.example.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo; this.encoder = encoder; }

    public UserEntity create(UserEntity u) {
        u.setPasswordHash(encoder.encode(u.getPasswordHash()));
        return repo.save(u);
    }
}
