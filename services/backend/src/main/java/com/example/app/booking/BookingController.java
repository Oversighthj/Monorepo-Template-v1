package com.example.app.booking;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.app.property.PropertyEntity;
import com.example.app.user.UserEntity;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
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
@RequestMapping("/bookings")
public class BookingController {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody BookingDTO dto) {
    bookingService.create(toEntity(dto));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<BookingDTO>> list(
      @RequestParam(required = false) Long propertyId,
      @RequestParam(required = false) Long userId) {
    List<BookingEntity> entities =
        bookingService.findAll(Optional.ofNullable(propertyId), Optional.ofNullable(userId));
    List<BookingDTO> dtos = entities.stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookingDTO> get(@PathVariable Long id) {
    BookingEntity entity = bookingService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(entity));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookingDTO> update(@PathVariable Long id, @Valid @RequestBody BookingDTO dto) {
    BookingEntity existing = bookingService.findById(id);
    if (existing == null) {
      return ResponseEntity.notFound().build();
    }
    BookingEntity toUpdate = toEntity(dto);
    toUpdate.setId(id);
    BookingEntity saved = bookingService.update(toUpdate);
    return ResponseEntity.ok(toDto(saved));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    bookingService.delete(id);
    return ResponseEntity.noContent().build();
  }

  private BookingDTO toDto(BookingEntity entity) {
    BookingDTO dto = new BookingDTO();
    dto.setId(entity.getId());
    dto.setPropertyId(entity.getProperty().getId());
    dto.setUserId(entity.getUser().getId());
    dto.setStart(entity.getStart());
    dto.setEnd(entity.getEnd());
    dto.setStatus(entity.getStatus());
    return dto;
  }

  private BookingEntity toEntity(BookingDTO dto) {
    BookingEntity entity = new BookingEntity();
    PropertyEntity prop = new PropertyEntity();
    prop.setId(dto.getPropertyId());
    entity.setProperty(prop);
    UserEntity user = new UserEntity();
    user.setId(dto.getUserId());
    entity.setUser(user);
    entity.setStart(dto.getStart());
    entity.setEnd(dto.getEnd());
    entity.setStatus(dto.getStatus());
    return entity;
  }
}
