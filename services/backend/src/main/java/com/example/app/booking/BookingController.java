package com.example.app.booking;

import com.example.app.api.BookingApi;
import com.example.app.model.BookingDTO;
import com.example.app.property.PropertyEntity;
import com.example.app.user.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController implements BookingApi {

  private final BookingService bookingService;

  public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
  }

  @Override
  public ResponseEntity<Void> createBooking(BookingDTO dto) {
    BookingEntity entity = toEntity(dto);
    bookingService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<BookingDTO>> getBookings(Long propertyId, Long userId) {
    List<BookingEntity> entities = bookingService.findByFilters(propertyId, userId);
    List<BookingDTO> dtos = entities.stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @Override
  public ResponseEntity<BookingDTO> getBooking(Long id) {
    BookingEntity entity = bookingService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(entity));
  }

  @Override
  public ResponseEntity<BookingDTO> updateBooking(Long id, BookingDTO dto) {
    BookingEntity entity = toEntity(dto);
    entity.setId(id);
    BookingEntity updated = bookingService.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(updated));
  }

  @Override
  public ResponseEntity<Void> deleteBooking(Long id) {
    bookingService.delete(id);
    return ResponseEntity.noContent().build();
  }

  private BookingEntity toEntity(BookingDTO dto) {
    BookingEntity entity = new BookingEntity();
    entity.setId(dto.getId());
    if (dto.getPropertyId() != null) {
      PropertyEntity prop = new PropertyEntity();
      prop.setId(dto.getPropertyId());
      entity.setProperty(prop);
    }
    if (dto.getUserId() != null) {
      UserEntity user = new UserEntity();
      user.setId(dto.getUserId());
      entity.setUser(user);
    }
    entity.setStartAt(dto.getStartAt());
    entity.setEndAt(dto.getEndAt());
    entity.setStatus(dto.getStatus());
    return entity;
  }

  private BookingDTO toDto(BookingEntity entity) {
    BookingDTO dto = new BookingDTO();
    dto.setId(entity.getId());
    if (entity.getProperty() != null) {
      dto.setPropertyId(entity.getProperty().getId());
    }
    if (entity.getUser() != null) {
      dto.setUserId(entity.getUser().getId());
    }
    dto.setStartAt(entity.getStartAt());
    dto.setEndAt(entity.getEndAt());
    dto.setStatus(entity.getStatus());
    return dto;
  }
}
