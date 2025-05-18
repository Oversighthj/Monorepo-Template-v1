package com.example.app.task;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingStatus;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import java.time.LocalDateTime;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TemplateApplication.class)   // ← غيّر الاسم لو لازم
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private UserRepository userRepository;
  @Autowired private BookingRepository bookingRepository;
  @Autowired private PropertyRepository propertyRepository;
  @Autowired private TaskRepository taskRepository;

  private Long cleanerId;
  private Long bookingId;

  @BeforeEach
  void setup() {
    taskRepository.deleteAll();
    bookingRepository.deleteAll();
    propertyRepository.deleteAll();
    userRepository.deleteAll();

    // users
    UserEntity cleaner =
        userRepository.save(new UserEntity(null, UserRole.CLEANER, "cleaner@test.io", "hash"));
    cleanerId = cleaner.getId();

    UserEntity guest =
        userRepository.save(new UserEntity(null, UserRole.GUEST, "guest@test.io", "hash"));

    UserEntity owner =
        userRepository.save(new UserEntity(null, UserRole.ADMIN, "owner@test.io", "hash"));

    // property
    PropertyEntity prop =
        propertyRepository.save(new PropertyEntity(null, "Unit 1", "123 Main", owner));

    // booking
    BookingEntity booking =
        bookingRepository.save(
            new BookingEntity(
                null,
                prop,
                guest,
                LocalDateTime.parse("2024-01-01T10:00"),
                LocalDateTime.parse("2024-01-02T10:00"),
                BookingStatus.CONFIRMED));
    bookingId = booking.getId();
  }

  @Test
  void postCreatesTaskAndListReturnsIt() throws Exception {
    String body =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleanerId);

    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());

    mockMvc
        .perform(get("/tasks"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].bookingId").value(bookingId))
        .andExpect(jsonPath("$[0].cleanerId").value(cleanerId));
  }

  @Test
  void filterByBookingIdReturnsOnlyBookingTasks() throws Exception {
    String body1 =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleanerId);
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body1))
        .andExpect(status().isCreated());

    // second booking + task
    UserEntity guest2 =
        userRepository.save(new UserEntity(null, UserRole.GUEST, "guest2@test.io", "hash"));
    PropertyEntity prop2 =
        propertyRepository.save(
            new PropertyEntity(null, "Unit 2", "456 Other", userRepository.findById(cleanerId).get()));
    BookingEntity booking2 =
        bookingRepository.save(
            new BookingEntity(
                null,
                prop2,
                guest2,
                LocalDateTime.parse("2024-02-01T10:00"),
                LocalDateTime.parse("2024-02-02T10:00"),
                BookingStatus.CONFIRMED));
    String body2 =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-02-01T12:00:00Z\"}",
            booking2.getId(), cleanerId);
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body2))
        .andExpect(status().isCreated());

    mockMvc
        .perform(get("/tasks?bookingId=" + bookingId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].bookingId").value(bookingId));
  }

  @Test
  void filterByCleanerIdReturnsOnlyCleanerTasks() throws Exception {
    String body1 =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleanerId);
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body1))
        .andExpect(status().isCreated());

    UserEntity cleaner2 =
        userRepository.save(new UserEntity(null, UserRole.CLEANER, "c2@test.io", "hash"));
    String body2 =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleaner2.getId());
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body2))
        .andExpect(status().isCreated());

    mockMvc
        .perform(get("/tasks?cleanerId=" + cleanerId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].cleanerId").value(cleanerId));
  }

  @Test
  void getByIdReturnsTaskOr404() throws Exception {
    String body =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleanerId);
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());
    Long taskId = taskRepository.findAll().get(0).getId();

    mockMvc.perform(get("/tasks/" + taskId)).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(taskId));
    mockMvc.perform(get("/tasks/9999")).andExpect(status().isNotFound());
  }

  @Test
  void putUpdatesStatus() throws Exception {
    String body =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleanerId);
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());
    Long taskId = taskRepository.findAll().get(0).getId();

    String update =
        String.format(
            "{\"id\":%d,\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"DONE\",\"due\":\"2024-01-01T12:00:00Z\"}",
            taskId, bookingId, cleanerId);
    mockMvc
        .perform(put("/tasks/" + taskId).contentType(MediaType.APPLICATION_JSON).content(update))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.status").value("DONE"));
  }

  @Test
  void deleteRemovesTask() throws Exception {
    String body =
        String.format(
            "{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"CLEAN\",\"status\":\"PENDING\",\"due\":\"2024-01-01T12:00:00Z\"}",
            bookingId, cleanerId);
    mockMvc
        .perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(status().isCreated());
    Long taskId = taskRepository.findAll().get(0).getId();

    mockMvc.perform(delete("/tasks/" + taskId)).andExpect(status().isNoContent());
    mockMvc.perform(get("/tasks/" + taskId)).andExpect(status().isNotFound());
  }
}
