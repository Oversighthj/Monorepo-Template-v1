package com.example.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
  public void testCreateAndListProperties() throws Exception {
    String userBody = "{\"role\":\"GUEST\",\"email\":\"owner@example.com\",\"passwordHash\":\"h\"}";
    mockMvc.perform(post("/users").contentType("application/json").content(userBody))
        .andExpect(status().isCreated());

    String propBody = "{\"name\":\"House\",\"address\":\"123 St\",\"ownerId\":1}";
    mockMvc.perform(post("/properties").contentType("application/json").content(propBody))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/properties").param("ownerId", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("House"));
  }
}
