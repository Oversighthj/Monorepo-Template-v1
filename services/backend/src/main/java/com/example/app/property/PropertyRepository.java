package com.example.app.property;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
  List<PropertyEntity> findByOwnerId(Long ownerId);
}
