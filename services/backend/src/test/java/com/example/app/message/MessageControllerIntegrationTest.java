package com.example.app.message;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.app.TemplateApplication;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingStatus;
import com.example.app.property.PropertyEntity;
import com.example.app.property.PropertyRepository;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TemplateApplication.class)
@AutoConfigureMockMvc
@org.springframework.transaction.annotation.Transactional
class MessageControllerIntegrationTest {

  @Autowired MockMvc mockMvc;
  @Autowired ObjectMapper mapper;

  @Autowired UserRepository userRepo;
  @Autowired PropertyRepository propertyRepo;
  @Autowired BookingRepository bookingRepo;
  @Autowired MessageRepository messageRepo;

  private Long bookingId;
  private Long senderId;

  @BeforeEach
  void setup() {
    messageRepo.deleteAll();
    bookingRepo.deleteAll();
    propertyRepo.deleteAll();
    userRepo.deleteAll();

    UserEntity guest = userRepo.save(new UserEntity(null, UserRole.GUEST, "guest@test.io", "hash"));
    senderId = guest.getId();

    UserEntity owner = userRepo.save(new UserEntity(null, UserRole.ADMIN, "owner@test.io", "hash"));
    PropertyEntity prop = propertyRepo.save(new PropertyEntity(null, "Prop", "addr", owner));

    BookingEntity booking =
        bookingRepo.save(
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
  void postThenGetReturnsMessage() throws Exception {
    String json =
        """
                {
                  "bookingId": %d,
                  "senderId": %d,
                  "content": "hello",
                  "sentAt": "2024-01-01T12:00:00Z"
                }
                """
            .formatted(bookingId, senderId);

    mockMvc
        .perform(post("/messages").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isCreated());

    mockMvc
        .perform(get("/messages?bookingId=" + bookingId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(1)))
        .andExpect(jsonPath("$[0].content").value("hello"));
  }
}
