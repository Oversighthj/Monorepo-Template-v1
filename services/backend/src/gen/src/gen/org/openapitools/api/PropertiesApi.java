package org.openapitools.api;

import org.openapitools.model.PropertyDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

public interface PropertiesApi {

  @PostMapping("/properties")
  ResponseEntity<Void> propertiesPost(@Valid @RequestBody PropertyDTO propertyDTO);

  @GetMapping("/properties")
  ResponseEntity<List<PropertyDTO>> propertiesGet(@RequestParam(value = "ownerId", required = false) Long ownerId);

  @GetMapping("/properties/{id}")
  ResponseEntity<PropertyDTO> propertiesIdGet(@PathVariable("id") Long id);

  @PutMapping("/properties/{id}")
  ResponseEntity<PropertyDTO> propertiesIdPut(@PathVariable("id") Long id, @Valid @RequestBody PropertyDTO propertyDTO);

  @DeleteMapping("/properties/{id}")
  ResponseEntity<Void> propertiesIdDelete(@PathVariable("id") Long id);
}
