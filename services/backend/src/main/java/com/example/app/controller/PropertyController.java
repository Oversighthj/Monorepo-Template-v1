package com.example.app.controller;

import com.example.app.model.PropertyDTO;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyService;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    propertyService.create(entity, dto.getOwnerId());
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
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

  @GetMapping("/{id}")
  public ResponseEntity<PropertyDTO> get(@PathVariable Long id) {
    PropertyEntity p = propertyService.findById(id);
    PropertyDTO dto = new PropertyDTO()
        .id(p.getId())
        .name(p.getName())
        .address(p.getAddress())
        .ownerId(p.getOwner().getId());
    return ResponseEntity.ok(dto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PropertyDTO> update(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
    PropertyEntity data = new PropertyEntity();
    data.setName(dto.getName());
    data.setAddress(dto.getAddress());
    PropertyEntity updated = propertyService.update(id, data, dto.getOwnerId());
    PropertyDTO out = new PropertyDTO()
        .id(updated.getId())
        .name(updated.getName())
        .address(updated.getAddress())
        .ownerId(updated.getOwner().getId());
    return ResponseEntity.ok(out);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
