package com.example.app.property;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
  List<PropertyEntity> findByOwnerId(Long ownerId);
}
