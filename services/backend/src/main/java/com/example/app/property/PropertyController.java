package com.example.app.property;

import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
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

/**
 * REST controller for CRUD operations on {@link PropertyEntity}.
 * <p>
 *   {@code ownerId} is mandatory and is resolved to a {@link UserEntity}
 *   via {@link UserRepository}. If the owner is missing, the API returns 400.
 * </p>
 */
@RestController
@RequestMapping("/properties")
public class PropertyController {

  private final PropertyService propertyService;
  private final UserRepository userRepository;

  public PropertyController(PropertyService propertyService, UserRepository userRepository) {
    this.propertyService = propertyService;
    this.userRepository = userRepository;
  }

  // ─────────────────────────────── create ─────
  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody PropertyDTO dto) {
    UserEntity owner = userRepository.findById(dto.getOwnerId())
        .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

    PropertyEntity entity = new PropertyEntity();
    entity.setName(dto.getName());
    entity.setAddress(dto.getAddress());
    entity.setOwner(owner);

    propertyService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  // ─────────────────────────────── list ─────
  @GetMapping
  public ResponseEntity<List<PropertyDTO>> list(@RequestParam(required = false) Long ownerId) {
    List<PropertyEntity> entities =
        ownerId != null ? propertyService.findByOwnerId(ownerId) : propertyService.findAll();

    List<PropertyDTO> dtos = entities.stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  // ─────────────────────────────── get ─────
  @GetMapping("/{id}")
  public ResponseEntity<PropertyDTO> get(@PathVariable Long id) {
    PropertyEntity entity = propertyService.findById(id);
    return entity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(toDto(entity));
  }

  // ─────────────────────────────── update ─────
  @PutMapping("/{id}")
  public ResponseEntity<PropertyDTO> update(
      @PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
    PropertyEntity entity = propertyService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }

    entity.setName(dto.getName());
    entity.setAddress(dto.getAddress());

    if (dto.getOwnerId() != null && !dto.getOwnerId().equals(entity.getOwner().getId())) {
      UserEntity owner = userRepository.findById(dto.getOwnerId())
          .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
      entity.setOwner(owner);
    }

    PropertyEntity saved = propertyService.update(entity);
    return ResponseEntity.ok(toDto(saved));
  }

  // ─────────────────────────────── delete ─────
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    propertyService.delete(id);
    return ResponseEntity.noContent().build();
  }

  // ─────────────────────────────── mapper ─────
  private PropertyDTO toDto(PropertyEntity entity) {
    PropertyDTO dto = new PropertyDTO();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setAddress(entity.getAddress());
    dto.setOwnerId(entity.getOwner().getId());
    return dto;
  }
}
