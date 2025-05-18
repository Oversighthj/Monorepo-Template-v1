package com.example.app.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class BookingControllerTests {

  @Autowired private MockMvc mockMvc;
  @Autowired private BookingService bookingService;
  @Autowired private UserRepository userRepository;
  @Autowired private PropertyRepository propertyRepository;

  private String createBody(Long propId, Long userId, String start, String end, String status) {
    return String.format(
        "{\"propertyId\":%d,\"userId\":%d,\"startAt\":\"%s\",\"endAt\":\"%s\",\"status\":\"%s\"}",
        propId, userId, start, end, status);
  }

  private PropertyEntity setupProperty(UserEntity user) {
    PropertyEntity prop = new PropertyEntity(null, "p", "addr", user);
    return propertyRepository.save(prop);
  }

  private UserEntity setupUser() {
    UserEntity user = new UserEntity(null, UserRole.GUEST, "u@example.com", "hash");
    return userRepository.save(user);
  }

  @Test
  public void createBookingReturns201() throws Exception {
    UserEntity user = setupUser();
    PropertyEntity prop = setupProperty(user);

    String body = createBody(prop.getId(), user.getId(), "2024-01-01T10:00:00", "2024-01-01T12:00:00", "PENDING");
    mockMvc
        .perform(post("/bookings").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());
  }

  @Test
  public void createOverlappingReturns409() throws Exception {
    UserEntity user = setupUser();
    PropertyEntity prop = setupProperty(user);

    BookingEntity first = new BookingEntity();
    first.setProperty(prop);
    first.setUser(user);
    first.setStartAt(LocalDateTime.of(2024,1,1,10,0));
    first.setEndAt(LocalDateTime.of(2024,1,1,12,0));
    first.setStatus(BookingStatus.PENDING);
    bookingService.create(first);

    String body = createBody(prop.getId(), user.getId(), "2024-01-01T11:00:00", "2024-01-01T13:00:00", "PENDING");
    mockMvc
        .perform(post("/bookings").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isConflict());
  }

  @Test
  public void listGetUpdateBookings() throws Exception {
    UserEntity user = setupUser();
    PropertyEntity prop = setupProperty(user);

    BookingEntity first = new BookingEntity();
    first.setProperty(prop);
    first.setUser(user);
    first.setStartAt(LocalDateTime.of(2024,1,1,10,0));
    first.setEndAt(LocalDateTime.of(2024,1,1,12,0));
    first.setStatus(BookingStatus.PENDING);
    first = bookingService.create(first);

    mockMvc
        .perform(get("/bookings").param("propertyId", prop.getId().toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(first.getId()));

    mockMvc
        .perform(get("/bookings/" + first.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("PENDING"));

    String body = createBody(prop.getId(), user.getId(), "2024-01-01T10:00:00", "2024-01-01T12:00:00", "CONFIRMED");
    mockMvc
        .perform(put("/bookings/" + first.getId()).contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("CONFIRMED"));
  }
}
