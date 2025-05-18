package com.example.app.property;

import com.example.app.user.UserEntity;
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

  public PropertyEntity create(PropertyEntity property) {
    return propertyRepository.save(property);
  }

  public Optional<PropertyEntity> findById(Long id) {
    return propertyRepository.findById(id);
  }

  public List<PropertyEntity> findAll(Long ownerId) {
    if (ownerId != null) {
      return propertyRepository.findByOwnerId(ownerId);
    }
    return propertyRepository.findAll();
  }

  public PropertyEntity update(Long id, PropertyEntity data) {
    PropertyEntity prop = propertyRepository.findById(id).orElseThrow();
    prop.setName(data.getName());
    prop.setAddress(data.getAddress());
    prop.setOwner(data.getOwner());
    return propertyRepository.save(prop);
  }

  public void delete(Long id) {
    propertyRepository.deleteById(id);
  }

  public UserEntity findOwner(Long ownerId) {
    return userRepository.findById(ownerId).orElseThrow();
  }
}
