package com.example.app.task;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * End-to-end tests for TaskController.
 */
@SpringBootTest(classes = TemplateApplication.class)
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper mapper;

    @Autowired UserRepository userRepo;
    @Autowired PropertyRepository propertyRepo;
    @Autowired BookingRepository bookingRepo;
    @Autowired TaskRepository taskRepo;

    private Long bookingId;
    private Long cleanerId;

    @BeforeEach
    void setUp() {
        taskRepo.deleteAll();
        bookingRepo.deleteAll();
        propertyRepo.deleteAll();
        userRepo.deleteAll();

        // ---- users ----
        UserEntity cleaner =
                userRepo.save(new UserEntity(null, UserRole.CLEANER, "cleaner@test.io", "hash"));
        cleanerId = cleaner.getId();

        UserEntity guest =
                userRepo.save(new UserEntity(null, UserRole.GUEST, "guest@test.io", "hash"));
        UserEntity owner =
                userRepo.save(new UserEntity(null, UserRole.ADMIN, "owner@test.io", "hash"));

        // ---- property ----
        PropertyEntity prop = propertyRepo.save(
        new PropertyEntity(null, "Unit 1", "123 Main", owner));

        // ---- booking ----
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

    /* ---------- helpers ---------- */
    private String newTaskJson(long bId, long cId) {
        return """
               {
                 "bookingId": %d,
                 "cleanerId": %d,
                 "type": "CLEAN",
                 "status": "PENDING",
                 "due": "2024-01-01T12:00:00Z"
               }
               """.formatted(bId, cId);
    }

    /* ---------- tests ---------- */

    @Test
    void postCreatesTaskAndListReturnsIt() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleanerId)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookingId").value(bookingId))
                .andExpect(jsonPath("$[0].cleanerId").value(cleanerId));
    }

    @Test
    void filterByBookingIdReturnsOnlyBookingTasks() throws Exception {
        // task for booking 1
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleanerId)))
                .andExpect(status().isCreated());

        // second property / booking
        UserEntity guest2 =
                userRepo.save(new UserEntity(null, UserRole.GUEST, "guest2@test.io", "hash"));
        PropertyEntity prop2 = propertyRepo.save(
        new PropertyEntity(null, "Unit 2", "456 Other", guest2));

        BookingEntity booking2 =
                bookingRepo.save(
                        new BookingEntity(
                                null,
                                prop2,
                                guest2,
                                LocalDateTime.parse("2024-02-01T10:00"),
                                LocalDateTime.parse("2024-02-02T10:00"),
                                BookingStatus.CONFIRMED));

        // task for booking 2
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(booking2.getId(), cleanerId)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tasks?bookingId=" + bookingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].bookingId").value(bookingId));
    }

    @Test
    void filterByCleanerIdReturnsOnlyCleanerTasks() throws Exception {
        // task for cleaner 1
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleanerId)))
                .andExpect(status().isCreated());

        // second cleaner
        UserEntity cleaner2 =
                userRepo.save(new UserEntity(null, UserRole.CLEANER, "c2@test.io", "hash"));
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleaner2.getId())))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tasks?cleanerId=" + cleanerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].cleanerId").value(cleanerId));
    }

    @Test
    void getByIdReturnsTaskOr404() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleanerId)))
                .andExpect(status().isCreated());

        Long taskId = taskRepo.findAll().get(0).getId();

        mockMvc.perform(get("/tasks/" + taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskId));

        mockMvc.perform(get("/tasks/99999")).andExpect(status().isNotFound());
    }

    @Test
    void putUpdatesStatus() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleanerId)))
                .andExpect(status().isCreated());

        Long taskId = taskRepo.findAll().get(0).getId();

        String updateJson =
                """
                {
                  "id": %d,
                  "bookingId": %d,
                  "cleanerId": %d,
                  "type": "CLEAN",
                  "status": "DONE",
                  "due": "2024-01-01T12:00:00Z"
                }
                """
                        .formatted(taskId, bookingId, cleanerId);

        mockMvc.perform(put("/tasks/" + taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DONE"));
    }

    @Test
    void deleteRemovesTask() throws Exception {
        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson(bookingId, cleanerId)))
                .andExpect(status().isCreated());

        Long taskId = taskRepo.findAll().get(0).getId();

        mockMvc.perform(delete("/tasks/" + taskId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/tasks/" + taskId)).andExpect(status().isNotFound());
    }
}
