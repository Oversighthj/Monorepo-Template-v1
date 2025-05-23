package com.example.app.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> findAllByOrderByIdAsc();
  Optional<UserEntity> findByEmail(String email);
}
