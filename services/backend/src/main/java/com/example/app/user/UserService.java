package com.example.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserEntity create(UserEntity user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public Optional<UserEntity> validateCredentials(String email, String raw) {
        return userRepository.findByEmail(email)
                .filter(u -> passwordEncoder.matches(raw, u.getPasswordHash()));
    }

    public List<UserEntity> findAll() {
        var list = userRepository.findAllByOrderByIdAsc();
        list.forEach(u -> u.setPasswordHash(null));
        return list;
    }
}
