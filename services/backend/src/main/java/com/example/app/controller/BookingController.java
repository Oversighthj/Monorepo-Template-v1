package com.example.app.controller;

import com.example.app.api.BookingApi;
import com.example.app.booking.BookingDTO;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingService;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyService;
import com.example.app.user.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController implements BookingApi {

  private final BookingService bookingService;
  private final BookingRepository bookingRepository;
  private final PropertyService propertyService;

  public BookingController(
      BookingService bookingService,
      BookingRepository bookingRepository,
      PropertyService propertyService) {
    this.bookingService = bookingService;
    this.bookingRepository = bookingRepository;
    this.propertyService = propertyService;
  }

  private BookingDTO toDto(BookingEntity entity) {
    BookingDTO dto = new BookingDTO();
    dto.setId(entity.getId());
    dto.setPropertyId(entity.getProperty() != null ? entity.getProperty().getId() : null);
    dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
    dto.setStartAt(entity.getStartAt());
    dto.setEndAt(entity.getEndAt());
    dto.setStatus(entity.getStatus());
    return dto;
  }

  private BookingEntity toEntity(BookingDTO dto) {
    BookingEntity entity = new BookingEntity();
    entity.setId(dto.getId());
    if (dto.getPropertyId() != null) {
      PropertyEntity p = propertyService.findById(dto.getPropertyId());
      entity.setProperty(p);
    }
    if (dto.getUserId() != null) {
      UserEntity u = new UserEntity();
      u.setId(dto.getUserId());
      entity.setUser(u);
    }
    entity.setStartAt(dto.getStartAt());
    entity.setEndAt(dto.getEndAt());
    entity.setStatus(dto.getStatus());
    return entity;
  }

  @Override
  public ResponseEntity<Void> bookingsPost(BookingDTO bookingDTO) {
    bookingService.create(toEntity(bookingDTO));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<BookingDTO>> bookingsGet(Long propertyId, Long userId) {
    List<BookingDTO> list = bookingRepository.findAll().stream()
        .filter(b -> propertyId == null ||
            (b.getProperty() != null && propertyId.equals(b.getProperty().getId())))
        .filter(b -> userId == null ||
            (b.getUser() != null && userId.equals(b.getUser().getId())))
        .map(this::toDto)
        .collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

  @Override
  public ResponseEntity<BookingDTO> bookingsIdGet(Long id) {
    BookingEntity entity = bookingService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(entity));
  }

  @Override
  public ResponseEntity<BookingDTO> bookingsIdPut(Long id, BookingDTO bookingDTO) {
    BookingEntity entity = toEntity(bookingDTO);
    entity.setId(id);
    BookingEntity updated = bookingService.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(updated));
  }

  @Override
  public ResponseEntity<Void> bookingsIdDelete(Long id) {
    bookingRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
