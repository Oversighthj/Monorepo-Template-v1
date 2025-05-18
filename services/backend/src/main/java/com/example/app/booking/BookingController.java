package com.example.app.booking;

import com.example.app.api.BookingApi;
import com.example.app.model.BookingDTO;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyService;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BookingController implements BookingApi {

  private final BookingService bookingService;
  private final PropertyService propertyService;
  private final UserRepository userRepository;
  private final BookingRepository bookingRepository;

  public BookingController(
      BookingService bookingService,
      PropertyService propertyService,
      UserRepository userRepository,
      BookingRepository bookingRepository) {
    this.bookingService = bookingService;
    this.propertyService = propertyService;
    this.userRepository = userRepository;
    this.bookingRepository = bookingRepository;
  }

  @Override
  public ResponseEntity<Void> bookingsPost(@Valid @RequestBody BookingDTO bookingDTO) {
    BookingEntity entity = fromDto(bookingDTO);
    bookingService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<BookingDTO>> bookingsGet(
      @RequestParam(value = "propertyId", required = false) Long propertyId,
      @RequestParam(value = "userId", required = false) Long userId) {
    List<BookingEntity> entities = bookingRepository.findAll();
    List<BookingEntity> filtered =
        entities.stream()
            .filter(
                b ->
                    propertyId == null
                        || (b.getProperty() != null && b.getProperty().getId().equals(propertyId)))
            .filter(
                b -> userId == null || (b.getUser() != null && b.getUser().getId().equals(userId)))
            .collect(Collectors.toList());
    List<BookingDTO> dtos = filtered.stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @Override
  public ResponseEntity<BookingDTO> bookingsIdGet(@PathVariable("id") Long id) {
    BookingEntity entity = bookingService.findById(id);
    if (entity == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(entity));
  }

  @Override
  public ResponseEntity<BookingDTO> bookingsIdPut(
      @PathVariable("id") Long id, @Valid @RequestBody BookingDTO bookingDTO) {
    BookingEntity entity = fromDto(bookingDTO);
    entity.setId(id);
    BookingEntity updated = bookingService.update(entity);
    if (updated == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(toDto(updated));
  }

  @Override
  public ResponseEntity<Void> bookingsIdDelete(@PathVariable("id") Long id) {
    bookingRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  private BookingDTO toDto(BookingEntity entity) {
    BookingDTO dto = new BookingDTO();
    dto.setId(entity.getId());
    dto.setPropertyId(entity.getProperty() != null ? entity.getProperty().getId() : null);
    dto.setUserId(entity.getUser() != null ? entity.getUser().getId() : null);
    dto.setStartAt(
        entity.getStartAt() != null
            ? entity.getStartAt().atOffset(OffsetDateTime.now().getOffset())
            : null);
    dto.setEndAt(
        entity.getEndAt() != null
            ? entity.getEndAt().atOffset(OffsetDateTime.now().getOffset())
            : null);
    dto.setStatus(
        entity.getStatus() != null
            ? com.example.app.model.BookingStatus.valueOf(entity.getStatus().name())
            : null);
    return dto;
  }

  private BookingEntity fromDto(BookingDTO dto) {
    PropertyEntity property =
        dto.getPropertyId() != null ? propertyService.findById(dto.getPropertyId()) : null;
    UserEntity user =
        dto.getUserId() != null ? userRepository.findById(dto.getUserId()).orElse(null) : null;
    return new BookingEntity(
        dto.getId(),
        property,
        user,
        dto.getStartAt() != null ? dto.getStartAt().toLocalDateTime() : null,
        dto.getEndAt() != null ? dto.getEndAt().toLocalDateTime() : null,
        dto.getStatus() != null
            ? com.example.app.booking.BookingStatus.valueOf(dto.getStatus().name())
            : null);
  }
}
