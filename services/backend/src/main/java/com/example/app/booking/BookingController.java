package com.example.app.booking;

import com.example.app.property.PropertyService;
import com.example.app.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

  private final BookingService bookingService;
  private final PropertyService propertyService;
  private final UserService userService;

  public BookingController(
      BookingService bookingService,
      PropertyService propertyService,
      UserService userService) {
    this.bookingService = bookingService;
    this.propertyService = propertyService;
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody BookingDTO dto) {
    BookingEntity entity = new BookingEntity();
    entity.setProperty(propertyService.findById(dto.getPropertyId()));
    entity.setUser(userService.findById(dto.getUserId()));
    entity.setStart(dto.getStartAt());
    entity.setEnd(dto.getEndAt());
    entity.setStatus(dto.getStatus());
    bookingService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public ResponseEntity<List<BookingDTO>> list(@RequestParam Long propertyId) {
    List<BookingDTO> dtos =
        bookingService.findByPropertyId(propertyId).stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
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
    dto.setStartAt(entity.getStart());
    dto.setEndAt(entity.getEnd());
    dto.setStatus(entity.getStatus());
    return dto;
  }
}
