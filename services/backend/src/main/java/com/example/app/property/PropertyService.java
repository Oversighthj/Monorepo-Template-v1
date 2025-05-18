package com.example.app.property;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

  private final PropertyRepository propertyRepository;

  public PropertyService(PropertyRepository propertyRepository) {
    this.propertyRepository = propertyRepository;
  }

  public PropertyEntity create(PropertyEntity property) {
    return propertyRepository.save(property);
  }

  public List<PropertyEntity> findAll() {
    return propertyRepository.findAll();
  }

  public PropertyEntity findById(Long id) {
    Optional<PropertyEntity> entity = propertyRepository.findById(id);
    return entity.orElse(null);
  }

  public PropertyEntity update(PropertyEntity property) {
    return propertyRepository.save(property);
  }

  public void delete(Long id) {
    propertyRepository.deleteById(id);
  }

  public List<PropertyEntity> findByOwnerId(Long ownerId) {
    return propertyRepository.findByOwnerId(ownerId);
  }
}
