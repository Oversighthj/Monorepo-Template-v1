package com.example.app.user;

<<<<<<< Updated upstream
import java.util.List;
=======
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

<<<<<<< Updated upstream
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
=======
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ---------------------------------------------------------------
    // CRUD BASICS
    // ---------------------------------------------------------------
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    /**
     * API مستعملة في FeatureController – تستقبل كائن جاهز.
     * إذا كان الـ password موجودًا كـ نص خام، يُشفّر تلقائيًا.
     */
    @Transactional
    public UserEntity create(UserEntity entity) {
        if (entity.getPasswordHash() != null && !entity.getPasswordHash().startsWith("$2")) {
            entity.setPasswordHash(passwordEncoder.encode(entity.getPasswordHash()));
        }
        return userRepository.save(entity);
    }
>>>>>>> Stashed changes

    /**
     * API مريحة استعملها AuthController وغيره.
     */
    @Transactional
    public UserEntity create(String email, String rawPassword, UserRole role) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        return userRepository.save(user);
    }

<<<<<<< Updated upstream
  public List<UserEntity> findAll() {
    return userRepository.findAllByOrderByIdAsc();
  }
=======
    // ---------------------------------------------------------------
    // AUTH / VALIDATION
    // ---------------------------------------------------------------
    public UserEntity authenticate(String email, String rawPassword) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("INVALID_CREDENTIALS");
        }
        return user;
    }

    /** يُستدعى من AuthController – مجرّد غلاف للوضوح. */
    public UserEntity validateCredentials(String email, String pass) {
        return authenticate(email, pass);
    }
>>>>>>> Stashed changes
}
