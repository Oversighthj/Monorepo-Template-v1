package com.example.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  public void testCreateAndListBookings() throws Exception {
    // create user
    String userBody =
        "{\"role\":\"GUEST\",\"email\":\"b@example.com\",\"passwordHash\":\"hash\"}";
    mockMvc
        .perform(post("/users").contentType("application/json").content(userBody))
        .andExpect(status().isCreated());

    // create property
    String propBody = "{\"name\":\"Test\",\"address\":\"Addr\"}";
    mockMvc
        .perform(post("/properties").contentType("application/json").content(propBody))
        .andExpect(status().isCreated());

    String booking =
        "{\"propertyId\":1,\"userId\":1,\"startAt\":\"2024-01-01T00:00:00\",\"endAt\":\"2024-01-05T00:00:00\",\"status\":\"NEW\"}";
    mockMvc
        .perform(post("/bookings").contentType("application/json").content(booking))
        .andExpect(status().isCreated());

    String overlap =
        "{\"propertyId\":1,\"userId\":1,\"startAt\":\"2024-01-03T00:00:00\",\"endAt\":\"2024-01-06T00:00:00\",\"status\":\"NEW\"}";
    mockMvc
        .perform(post("/bookings").contentType("application/json").content(overlap))
        .andExpect(status().isConflict());

    mockMvc
        .perform(get("/bookings").param("propertyId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].propertyId").value(1))
        .andExpect(jsonPath("$[0].startAt").value("2024-01-01T00:00:00"));
  }
}
