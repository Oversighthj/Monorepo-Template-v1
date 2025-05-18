package com.example.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/** اختبار وحدة لمسار GET /feature للتحقق من أنه يرجع قائمة الميزات بنجاح. */
@SpringBootTest
@AutoConfigureMockMvc
public class TemplateApplicationTests {

  @Autowired private MockMvc mockMvc;

  @Test
  public void testGetFeatureReturnsList() throws Exception {
    // تنفيذ طلب GET إلى '/feature' والتحقق من حالة 200 ومن صحة المحتوى المتوقع
    mockMvc
        .perform(get("/feature"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"id\":1,\"name\":\"Sample feature\"}]"));
  }

  @Test
  public void testCreateAndListUsers() throws Exception {
    String body = "{\"role\":\"GUEST\",\"email\":\"a@example.com\",\"passwordHash\":\"hash\"}";

    mockMvc
        .perform(post("/users").contentType("application/json").content(body))
        .andExpect(status().isCreated());

    mockMvc
        .perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].email").value("a@example.com"))
        .andExpect(jsonPath("$[0].passwordHash").doesNotExist());
  }

  @Test
  public void testBookingOverlap() throws Exception {
    String owner = "{\"role\":\"ADMIN\",\"email\":\"owner@example.com\",\"passwordHash\":\"hash\"}";
    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(owner))
        .andExpect(status().isCreated());

    String guest = "{\"role\":\"GUEST\",\"email\":\"guest@example.com\",\"passwordHash\":\"hash\"}";
    mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(guest))
        .andExpect(status().isCreated());

    String property = "{\"name\":\"House\",\"address\":\"addr\",\"ownerId\":1}";
    mockMvc.perform(post("/properties").contentType(MediaType.APPLICATION_JSON).content(property))
        .andExpect(status().isCreated());

    String booking1 = "{\"propertyId\":1,\"userId\":2,\"start\":\"2024-01-01T10:00:00\",\"end\":\"2024-01-02T10:00:00\",\"status\":\"CONFIRMED\"}";
    mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON).content(booking1))
        .andExpect(status().isCreated());

    String booking2 = "{\"propertyId\":1,\"userId\":2,\"start\":\"2024-01-01T12:00:00\",\"end\":\"2024-01-02T09:00:00\",\"status\":\"CONFIRMED\"}";
    mockMvc.perform(post("/bookings").contentType(MediaType.APPLICATION_JSON).content(booking2))
        .andExpect(status().isConflict());

    mockMvc.perform(get("/bookings").param("propertyId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].userId").value(2));
  }
}
