package com.example.app.controller;

import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyService;
import com.example.app.user.UserEntity;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.openapitools.model.PropertyDTO;

@RestController
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> create(@Valid @RequestBody PropertyDTO dto) {
        PropertyEntity entity = new PropertyEntity(null, dto.getName(), dto.getAddress(), ownerFromId(dto.getOwnerId()));
        PropertyEntity saved = propertyService.create(entity);
        PropertyDTO result = toDto(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> list(@RequestParam(name = "ownerId", required = false) Long ownerId) {
        List<PropertyEntity> entities = (ownerId == null) ? propertyService.findAll() : propertyService.findByOwnerId(ownerId);
        List<PropertyDTO> dtos = entities.stream().map(this::toDto).toList();
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
    public ResponseEntity<PropertyDTO> update(@PathVariable Long id, @Valid @RequestBody PropertyDTO dto) {
        PropertyEntity entity = new PropertyEntity(id, dto.getName(), dto.getAddress(), ownerFromId(dto.getOwnerId()));
        PropertyEntity saved = propertyService.update(id, entity);
        return ResponseEntity.ok(toDto(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PropertyDTO toDto(PropertyEntity entity) {
        return new PropertyDTO()
            .id(entity.getId())
            .name(entity.getName())
            .address(entity.getAddress())
            .ownerId(entity.getOwner().getId());
    }

    private UserEntity ownerFromId(Long ownerId) {
        UserEntity user = new UserEntity();
        user.setId(ownerId);
        return user;
    }
}
