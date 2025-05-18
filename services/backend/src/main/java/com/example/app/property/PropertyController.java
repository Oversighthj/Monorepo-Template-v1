package com.example.app.property;

import jakarta.validation.Valid;
import java.util.List;
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
    PropertyEntity entity = new PropertyEntity();
    entity.setName(dto.getName());
    entity.setAddress(dto.getAddress());
    // owner not set due to simplification
    propertyService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<PropertyDTO>> list(@RequestParam(required = false) Long ownerId) {
    List<PropertyEntity> entities =
        ownerId != null ? propertyService.findByOwnerId(ownerId) : propertyService.findAll();
    List<PropertyDTO> dtos = entities.stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PropertyDTO> get(@PathVariable Long id) {
    PropertyEntity entity = propertyService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PropertyDTO> update(
      @PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
    PropertyEntity entity = propertyService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    entity.setName(dto.getName());
    entity.setAddress(dto.getAddress());
    PropertyEntity saved = propertyService.update(entity);
    return ResponseEntity.ok(toDto(saved));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }

  private PropertyDTO toDto(PropertyEntity entity) {
    PropertyDTO dto = new PropertyDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setAddress(entity.getAddress());
    return dto;
  }
}
