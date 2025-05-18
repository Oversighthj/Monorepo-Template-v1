package com.example.app.controller;

import com.example.app.api.DefaultApi;
import com.example.app.model.PropertyDTO;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyService;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController implements DefaultApi {

  private final PropertyService propertyService;
  private final UserRepository userRepository;

  public PropertyController(PropertyService propertyService, UserRepository userRepository) {
    this.propertyService = propertyService;
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<List<PropertyDTO>> propertiesGet(Long ownerId) {
    List<PropertyDTO> list = propertyService.findAll(ownerId).stream()
        .map(e -> new PropertyDTO()
            .id(e.getId())
            .name(e.getName())
            .address(e.getAddress())
            .ownerId(e.getOwner().getId()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

  @Override
  public ResponseEntity<Void> propertiesPost(@Valid PropertyDTO propertyDTO) {
    UserEntity owner = userRepository.findById(propertyDTO.getOwnerId()).orElse(null);
    if (owner == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    PropertyEntity entity = new PropertyEntity(
        null,
        propertyDTO.getName(),
        propertyDTO.getAddress(),
        owner);
    propertyService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<PropertyDTO> propertiesIdGet(Long id) {
    return propertyService.findById(id)
        .map(e -> new PropertyDTO()
            .id(e.getId())
            .name(e.getName())
            .address(e.getAddress())
            .ownerId(e.getOwner().getId()))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<PropertyDTO> propertiesIdPut(Long id, @Valid PropertyDTO propertyDTO) {
    UserEntity owner = userRepository.findById(propertyDTO.getOwnerId()).orElse(null);
    if (owner == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    PropertyEntity entity = new PropertyEntity(
        id,
        propertyDTO.getName(),
        propertyDTO.getAddress(),
        owner);
    PropertyEntity updated = propertyService.update(entity);
    PropertyDTO dto = new PropertyDTO()
        .id(updated.getId())
        .name(updated.getName())
        .address(updated.getAddress())
        .ownerId(updated.getOwner().getId());
    return ResponseEntity.ok(dto);
  }

  @Override
  public ResponseEntity<Void> propertiesIdDelete(Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
