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

  public PropertyEntity create(PropertyEntity entity) {
    return propertyRepository.save(entity);
  }

  public List<PropertyEntity> findAll(Optional<Long> ownerId) {
    if (ownerId.isPresent()) {
      return propertyRepository.findByOwnerId(ownerId.get());
    }
    return propertyRepository.findAll();
  }

  public Optional<PropertyEntity> findById(Long id) {
    return propertyRepository.findById(id);
  }

  public Optional<PropertyEntity> update(Long id, PropertyEntity data) {
    return propertyRepository
        .findById(id)
        .map(existing -> {
          existing.setName(data.getName());
          existing.setAddress(data.getAddress());
          existing.setOwner(data.getOwner());
          return propertyRepository.save(existing);
        });
  }

  public void delete(Long id) {
    propertyRepository.deleteById(id);
  }

  public Optional<UserEntity> findOwner(Long ownerId) {
    return userRepository.findById(ownerId);
  }
}
