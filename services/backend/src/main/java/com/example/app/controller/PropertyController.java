package com.example.app.controller;

import com.example.app.model.PropertyDTO;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyService;
import com.example.app.user.UserEntity;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/properties")
public class PropertyController {

  private final PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody PropertyDTO dto) {
    Optional<UserEntity> owner = propertyService.findOwner(dto.getOwnerId());
    if (owner.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    PropertyEntity entity = new PropertyEntity(null, dto.getName(), dto.getAddress(), owner.get());
    propertyService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<PropertyDTO>> list(@RequestParam(required = false) Long ownerId) {
    List<PropertyDTO> properties = propertyService.findAll(Optional.ofNullable(ownerId)).stream()
        .map(p -> new PropertyDTO()
            .id(p.getId())
            .name(p.getName())
            .address(p.getAddress())
            .ownerId(p.getOwner().getId()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(properties);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PropertyDTO> get(@PathVariable Long id) {
    return propertyService
        .findById(id)
        .map(p -> new PropertyDTO()
            .id(p.getId())
            .name(p.getName())
            .address(p.getAddress())
            .ownerId(p.getOwner().getId()))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
    Optional<UserEntity> owner = propertyService.findOwner(dto.getOwnerId());
    if (owner.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    PropertyEntity entity = new PropertyEntity(id, dto.getName(), dto.getAddress(), owner.get());
    return propertyService.update(id, entity)
        .map(p -> ResponseEntity.ok().<Void>build())
        .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
