package com.example.app.property;

import com.example.app.model.PropertyDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
  private final PropertyService propertyService;

  public PropertyController(PropertyService propertyService) {
    this.propertyService = propertyService;
  }

  @PostMapping("/properties")
  public ResponseEntity<Void> create(@Valid @RequestBody PropertyDTO dto) {
    var owner = propertyService.findOwner(dto.getOwnerId());
    PropertyEntity entity = new PropertyEntity(null, dto.getName(), dto.getAddress(), owner);
    propertyService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/properties")
  public ResponseEntity<List<PropertyDTO>> list(@RequestParam(required = false) Long ownerId) {
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
  public ResponseEntity<PropertyDTO> get(@PathVariable Long id) {
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
  public ResponseEntity<PropertyDTO> update(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
    var owner = propertyService.findOwner(dto.getOwnerId());
    PropertyEntity data = new PropertyEntity(id, dto.getName(), dto.getAddress(), owner);
    PropertyEntity updated = propertyService.update(id, data);
    PropertyDTO out = new PropertyDTO()
        .id(updated.getId())
        .name(updated.getName())
        .address(updated.getAddress())
        .ownerId(updated.getOwner().getId());
    return ResponseEntity.ok(out);
  }

  @DeleteMapping("/properties/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
