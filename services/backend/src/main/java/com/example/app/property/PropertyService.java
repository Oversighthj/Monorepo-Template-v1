package com.example.app.property;

import com.example.app.user.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

  private final PropertyRepository propertyRepository;
  private final UserRepository userRepository;

  public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository) {
    this.propertyRepository = propertyRepository;
    this.userRepository = userRepository;
  }

  public PropertyEntity create(PropertyEntity property, Long ownerId) {
    property.setOwner(userRepository.findById(ownerId).orElseThrow());
    return propertyRepository.save(property);
  }

  public List<PropertyEntity> findAll(Long ownerId) {
    if (ownerId == null) {
      return propertyRepository.findAll();
    }
    return propertyRepository.findByOwnerId(ownerId);
  }

  public Optional<PropertyEntity> findById(Long id) {
    return propertyRepository.findById(id);
  }

  public PropertyEntity update(Long id, PropertyEntity property, Long ownerId) {
    property.setId(id);
    property.setOwner(userRepository.findById(ownerId).orElseThrow());
    return propertyRepository.save(property);
  }

  public void delete(Long id) {
    propertyRepository.deleteById(id);
  }
}
