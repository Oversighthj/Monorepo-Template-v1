package com.example.app.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTests {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testBookingCreationOverlapAndList() throws Exception {
    String userJson = "{\"role\":\"GUEST\",\"email\":\"b@example.com\",\"passwordHash\":\"x\"}";
    mockMvc.perform(post("/users").contentType("application/json").content(userJson))
        .andExpect(status().isCreated());

    String propertyJson = "{\"name\":\"House\",\"address\":\"Street\"}";
    mockMvc.perform(post("/properties").contentType("application/json").content(propertyJson))
        .andExpect(status().isCreated());

    String bookingJson = "{\"propertyId\":1,\"userId\":1,\"start\":\"2024-01-01T10:00:00\",\"end\":\"2024-01-02T10:00:00\",\"status\":\"CONFIRMED\"}";
    mockMvc.perform(post("/bookings").contentType("application/json").content(bookingJson))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/bookings").contentType("application/json").content(bookingJson))
        .andExpect(status().isConflict());

    mockMvc.perform(get("/bookings").param("propertyId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].propertyId").value(1));
  }
}
