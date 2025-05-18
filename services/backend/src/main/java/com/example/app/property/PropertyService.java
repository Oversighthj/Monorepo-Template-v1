package com.example.app.property;

import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import java.util.List;
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
    UserEntity owner = userRepository.findById(ownerId).orElseThrow();
    property.setOwner(owner);
    return propertyRepository.save(property);
  }

  public List<PropertyEntity> findAll(Long ownerId) {
    if (ownerId != null) {
      return propertyRepository.findByOwnerId(ownerId);
    }
    return propertyRepository.findAll();
  }

  public PropertyEntity findById(Long id) {
    return propertyRepository.findById(id).orElseThrow();
  }

  public PropertyEntity update(Long id, PropertyEntity data, Long ownerId) {
    PropertyEntity existing = findById(id);
    existing.setName(data.getName());
    existing.setAddress(data.getAddress());
    if (ownerId != null && (existing.getOwner() == null || !ownerId.equals(existing.getOwner().getId()))) {
      UserEntity owner = userRepository.findById(ownerId).orElseThrow();
      existing.setOwner(owner);
    }
    return propertyRepository.save(existing);
  }

  public void delete(Long id) {
    propertyRepository.deleteById(id);
  }
}
