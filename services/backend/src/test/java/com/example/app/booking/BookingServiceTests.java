package com.example.app.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@Transactional
public class BookingServiceTests {

  @Autowired private BookingService bookingService;
  @Autowired private UserRepository userRepository;
  @Autowired private PropertyRepository propertyRepository;

  @Test
  public void createOverlappingBookingReturnsConflict() {
    UserEntity user = new UserEntity(null, UserRole.GUEST, "u@example.com", "hash");
    user = userRepository.save(user);
    PropertyEntity prop = new PropertyEntity(null, "p", "addr", user);
    prop = propertyRepository.save(prop);

    BookingEntity first = new BookingEntity();
    first.setProperty(prop);
    first.setUser(user);
    first.setStartAt(LocalDateTime.of(2024, 1, 1, 10, 0));
    first.setEndAt(LocalDateTime.of(2024, 1, 1, 12, 0));
    first.setStatus("NEW");
    bookingService.create(first);

    BookingEntity overlap = new BookingEntity();
    overlap.setProperty(prop);
    overlap.setUser(user);
    overlap.setStartAt(LocalDateTime.of(2024, 1, 1, 11, 0));
    overlap.setEndAt(LocalDateTime.of(2024, 1, 1, 13, 0));
    overlap.setStatus("NEW");

    ResponseStatusException ex =
        assertThrows(ResponseStatusException.class, () -> bookingService.create(overlap));
    assertEquals(HttpStatus.CONFLICT, ex.getStatusCode());
  }
}
