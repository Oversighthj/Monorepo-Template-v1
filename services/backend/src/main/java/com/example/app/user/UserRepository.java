package com.example.app.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  List<UserEntity> findAllByOrderByIdAsc();
}
