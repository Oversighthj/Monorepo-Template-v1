package com.example.app.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingStatus;
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
public class TaskControllerIntegrationTests {

  @Autowired private MockMvc mockMvc;
  @Autowired private TaskRepository taskRepository;
  @Autowired private BookingRepository bookingRepository;
  @Autowired private PropertyRepository propertyRepository;
  @Autowired private UserRepository userRepository;

  @Test
  public void createTaskPersistsEntity() throws Exception {
    UserEntity cleaner = new UserEntity(null, UserRole.CLEANER, "c@example.com", "h");
    cleaner = userRepository.save(cleaner);
    UserEntity guest = new UserEntity(null, UserRole.GUEST, "g@example.com", "h");
    guest = userRepository.save(guest);
    PropertyEntity prop = new PropertyEntity(null, "p", "addr", guest);
    prop = propertyRepository.save(prop);

    BookingEntity booking = new BookingEntity();
    booking.setProperty(prop);
    booking.setUser(guest);
    booking.setStartAt(LocalDateTime.now());
    booking.setEndAt(LocalDateTime.now().plusHours(1));
    booking.setStatus(BookingStatus.PENDING);
    booking = bookingRepository.save(booking);

    String body =
        "{" +
            "\"bookingId\":" + booking.getId() + "," +
            "\"cleanerId\":" + cleaner.getId() + "," +
            "\"type\":\"CLEAN\"," +
            "\"status\":\"DONE\"," +
            "\"due\":\"2024-01-01T10:00:00Z\"}";

    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());

    TaskEntity saved = taskRepository.findAll().get(0);
    assertEquals(booking.getId(), saved.getBooking().getId());
    assertEquals(cleaner.getId(), saved.getCleaner().getId());
    assertEquals(TaskStatus.PENDING.name(), saved.getStatus());
  }
}
