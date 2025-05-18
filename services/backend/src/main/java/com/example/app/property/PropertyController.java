package com.example.app.property;

import java.util.List;
import java.util.stream.Collectors;
import org.openapitools.model.PropertyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
public class PropertyController {

  private final PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @PostMapping("/properties")
  public ResponseEntity<Void> createProperty(@Valid @RequestBody PropertyDTO dto) {
    PropertyEntity entity = new PropertyEntity(null, dto.getName(), dto.getAddress(), null);
    propertyService.create(entity, dto.getOwnerId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/properties")
  public ResponseEntity<List<PropertyDTO>> listProperties(@RequestParam(required = false) Long ownerId) {
    List<PropertyDTO> result = propertyService.findAll(ownerId).stream()
        .map(p -> new PropertyDTO()
            .id(p.getId())
            .name(p.getName())
            .address(p.getAddress())
            .ownerId(p.getOwner().getId()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(result);
  }

  @GetMapping("/properties/{id}")
  public ResponseEntity<PropertyDTO> getProperty(@PathVariable Long id) {
    return propertyService.findById(id)
        .map(p -> new PropertyDTO()
            .id(p.getId())
            .name(p.getName())
            .address(p.getAddress())
            .ownerId(p.getOwner().getId()))
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/properties/{id}")
  public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
    PropertyEntity entity = new PropertyEntity(id, dto.getName(), dto.getAddress(), null);
    PropertyEntity saved = propertyService.update(id, entity, dto.getOwnerId());
    PropertyDTO out = new PropertyDTO()
        .id(saved.getId())
        .name(saved.getName())
        .address(saved.getAddress())
        .ownerId(saved.getOwner().getId());
    return ResponseEntity.ok(out);
  }

  @DeleteMapping("/properties/{id}")
  public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
