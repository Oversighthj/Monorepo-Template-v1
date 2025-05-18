package com.example.app.task;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingRepository;
import com.example.app.booking.BookingStatus;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import com.example.app.user.UserRole;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TaskControllerIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private UserRepository userRepository;
    @Autowired private BookingRepository bookingRepository;

    private Long cleanerId;
    private Long bookingId;
    private Long guestId;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        bookingRepository.deleteAll();
        UserEntity cleaner = new UserEntity(null, UserRole.CLEANER, "c@example.com", "p");
        cleaner = userRepository.save(cleaner);
        cleanerId = cleaner.getId();
        UserEntity guest = new UserEntity(null, UserRole.GUEST, "g@example.com", "p");
        guest = userRepository.save(guest);
        guestId = guest.getId();
        BookingEntity booking = new BookingEntity(null, null, guest,
                LocalDateTime.of(2025,1,1,10,0),
                LocalDateTime.of(2025,1,1,12,0),
                BookingStatus.PENDING);
        booking = bookingRepository.save(booking);
        bookingId = booking.getId();
    }

    @Test
    void postCreatesTaskAndListReturnsIt() throws Exception {
        String body = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"clean\",\"status\":\"PENDING\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getTasksFilterByBooking() throws Exception {
        String body1 = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t1\",\"status\":\"PENDING\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body1))
                .andExpect(status().isCreated());

        BookingEntity booking2 = new BookingEntity(null, null, userRepository.findById(guestId).orElseThrow(),
                LocalDateTime.of(2025,1,2,10,0), LocalDateTime.of(2025,1,2,12,0), BookingStatus.PENDING);
        booking2 = bookingRepository.save(booking2);
        String body2 = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t2\",\"status\":\"PENDING\",\"due\":\"2025-01-03T10:00:00Z\"}", booking2.getId(), cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body2))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tasks").param("bookingId", bookingId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].bookingId").value(bookingId));
    }

    @Test
    void getTasksFilterByCleaner() throws Exception {
        String body1 = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t1\",\"status\":\"PENDING\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body1))
                .andExpect(status().isCreated());

        UserEntity cleaner2 = userRepository.save(new UserEntity(null, UserRole.CLEANER, "c2@example.com", "p"));
        String body2 = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t2\",\"status\":\"PENDING\",\"due\":\"2025-01-03T10:00:00Z\"}", bookingId, cleaner2.getId());
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body2))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/tasks").param("cleanerId", cleanerId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].cleanerId").value(cleanerId));
    }

    @Test
    void getTaskById() throws Exception {
        String body = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t\",\"status\":\"PENDING\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());

        // fetch list to get id
        String list = mockMvc.perform(get("/tasks"))
                .andReturn().getResponse().getContentAsString();
        // naive parse to get id of first element
        long id = com.fasterxml.jackson.databind.json.JsonMapper.builder().build()
                .readTree(list).get(0).get("id").asLong();

        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(get("/tasks/" + (id + 9999)))
                .andExpect(status().isNotFound());
    }

    @Test
    void putUpdatesTask() throws Exception {
        String body = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t\",\"status\":\"PENDING\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());

        String list = mockMvc.perform(get("/tasks"))
                .andReturn().getResponse().getContentAsString();
        long id = com.fasterxml.jackson.databind.json.JsonMapper.builder().build()
                .readTree(list).get(0).get("id").asLong();

        String putBody = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t\",\"status\":\"DONE\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(put("/tasks/" + id).contentType(MediaType.APPLICATION_JSON).content(putBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("DONE"));
    }

    @Test
    void deleteTask() throws Exception {
        String body = String.format("{\"bookingId\":%d,\"cleanerId\":%d,\"type\":\"t\",\"status\":\"PENDING\",\"due\":\"2025-01-02T10:00:00Z\"}", bookingId, cleanerId);
        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());

        String list = mockMvc.perform(get("/tasks"))
                .andReturn().getResponse().getContentAsString();
        long id = com.fasterxml.jackson.databind.json.JsonMapper.builder().build()
                .readTree(list).get(0).get("id").asLong();

        mockMvc.perform(delete("/tasks/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/tasks/" + id))
                .andExpect(status().isNotFound());
    }
}
