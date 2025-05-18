package com.example.app.property;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.property.PropertyDTO;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class PropertyService {

  private final PropertyRepository propertyRepository;
  private final UserRepository userRepository;

  public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository) {
    this.propertyRepository = propertyRepository;
    this.userRepository = userRepository;
  }

  public PropertyEntity create(PropertyDTO dto) {
    UserEntity owner = userRepository
        .findById(dto.getOwnerId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "owner not found"));
    PropertyEntity entity = new PropertyEntity();
    entity.setName(dto.getName());
    entity.setAddress(dto.getAddress());
    entity.setOwner(owner);
    return propertyRepository.save(entity);
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
