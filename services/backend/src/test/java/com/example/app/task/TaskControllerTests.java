package com.example.app.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
import com.example.app.booking.BookingStatus;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import com.example.app.task.TaskRepository;
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
public class TaskControllerTests {

  @Autowired private MockMvc mockMvc;
  @Autowired private TaskRepository taskRepository;
  @Autowired private PropertyRepository propertyRepository;
  @Autowired private BookingService bookingService;
  @Autowired private UserRepository userRepository;

  @Test
  public void postTasksCreatesTask() throws Exception {
    UserEntity owner = new UserEntity(null, UserRole.GUEST, "u@example.com", "hash");
    owner = userRepository.save(owner);
    UserEntity cleaner = new UserEntity(null, UserRole.CLEANER, "c@example.com", "hash");
    cleaner = userRepository.save(cleaner);

    PropertyEntity property = new PropertyEntity(null, "prop", "addr", owner);
    property = propertyRepository.save(property);

    BookingEntity booking = new BookingEntity(null, property, owner, LocalDateTime.now(), LocalDateTime.now().plusHours(1), BookingStatus.PENDING);
    booking = bookingService.create(booking);

    String body = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"clean\",\"status\":\"PENDING\",\"due\":\"2024-01-01T10:00:00Z\"}", booking.getId(), cleaner.getId());

    mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body)).andExpect(status().isCreated());

    assertEquals(1, taskRepository.count());
  }
}
