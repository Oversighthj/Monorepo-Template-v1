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

  public List<PropertyEntity> findAll(Long ownerId) {
    if (ownerId != null) {
      return propertyRepository.findByOwnerId(ownerId);
    }
    return propertyRepository.findAll();
  }

  public Optional<PropertyEntity> findById(Long id) {
    return propertyRepository.findById(id);
  }

  public PropertyEntity update(PropertyEntity property) {
    return propertyRepository.save(property);
  }

  public void delete(Long id) {
    propertyRepository.deleteById(id);
  }
}
