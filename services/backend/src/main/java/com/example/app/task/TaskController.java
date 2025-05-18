package com.example.app.task;

import com.example.app.api.TaskApi;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
import com.example.app.model.TaskDTO;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;
    private final BookingService bookingService;
    private final UserRepository userRepository;

    /* ---------- POST /tasks ---------- */
    @Override
    public ResponseEntity<Void> tasksPost(@Valid @RequestBody TaskDTO dto) {
        TaskEntity entity = fromDto(dto);
        entity.setStatus(TaskStatus.PENDING);               // force default
        taskService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* ---------- GET /tasks (list + filters) ---------- */
    @Override
    public ResponseEntity<List<TaskDTO>> tasksGet(Long bookingId, Long cleanerId) {
        List<TaskDTO> list = taskService.findAll().stream()
                .filter(t -> bookingId == null || t.getBooking().getId().equals(bookingId))
                .filter(t -> cleanerId == null || t.getCleaner().getId().equals(cleanerId))
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    /* ---------- GET /tasks/{id} ---------- */
    @Override
    public ResponseEntity<TaskDTO> tasksIdGet(Long id) {
        TaskEntity e = taskService.findById(id);
        if (e == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(e));
    }

    /* ---------- PUT /tasks/{id} ---------- */
    @Override
    public ResponseEntity<TaskDTO> tasksIdPut(Long id,
                                              @Valid @RequestBody TaskDTO dto) {

        TaskEntity toUpdate = fromDto(dto);
        toUpdate.setId(id);

        TaskEntity updated = taskService.update(toUpdate);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(updated));
    }

    /* ---------- DELETE /tasks/{id} ---------- */
    @Override
    public ResponseEntity<Void> tasksIdDelete(Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ---------- helper mappers ---------- */
    private TaskDTO toDto(TaskEntity e) {
        TaskDTO d = new TaskDTO();
        d.setId(e.getId());
        d.setBookingId(e.getBooking().getId());
        d.setCleanerId(e.getCleaner().getId());
        d.setType(e.getType());
        d.setStatus(com.example.app.model.TaskStatus.valueOf(e.getStatus().name()));
        d.setDue(e.getDue().atOffset(OffsetDateTime.now().getOffset()));
        return d;
    }

    private TaskEntity fromDto(TaskDTO dto) {
        BookingEntity booking = bookingService.findById(dto.getBookingId());
        if (booking == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }
        UserEntity cleaner = userRepository.findById(dto.getCleanerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cleaner not found"));

        return new TaskEntity(
                dto.getId(),
                booking,
                cleaner,
                dto.getType(),
                dto.getStatus() != null
                        ? TaskStatus.valueOf(dto.getStatus().name())
                        : TaskStatus.PENDING,
                dto.getDue().toLocalDateTime()
        );
    }
}
