package com.example.app.task;

import com.example.app.api.TaskApi;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
import com.example.app.model.TaskDTO;
import com.example.app.task.TaskEntity;
import com.example.app.task.TaskStatus;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {
    private final TaskService taskService;
    private final BookingService bookingService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Void> tasksPost(@Valid @RequestBody TaskDTO dto) {
        BookingEntity booking = bookingService.findById(dto.getBookingId());
        if (booking == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");

        UserEntity cleaner = userRepository.findById(dto.getCleanerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cleaner not found"));

        TaskEntity entity = new TaskEntity(
                null,
                booking,
                cleaner,
                dto.getType(),
                TaskStatus.PENDING,
                dto.getDue().toLocalDateTime()
        );
        taskService.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<TaskDTO>> tasksGet(Long bookingId, Long cleanerId) {
        List<TaskDTO> list = taskService.findAll().stream()
            .filter(t -> bookingId == null || t.getBooking().getId().equals(bookingId))
            .filter(t -> cleanerId == null || t.getCleaner().getId().equals(cleanerId))
            .map(this::toDto)
            .toList();
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<TaskDTO> tasksIdGet(@PathVariable("id") Long id) {
        TaskEntity entity = taskService.findById(id);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(entity));
    }

    @Override
    public ResponseEntity<TaskDTO> tasksIdPut(@PathVariable("id") Long id, @Valid @RequestBody TaskDTO dto) {
        TaskEntity entity = fromDto(dto, id);

        TaskEntity updated = taskService.update(entity);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDto(updated));
    }

    @Override
    public ResponseEntity<Void> tasksIdDelete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();

    public ResponseEntity<TaskDTO> tasksIdPut(Long id, @Valid @RequestBody TaskDTO dto) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<TaskDTO> tasksIdGet(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<Void> tasksIdDelete(Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

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

    private TaskEntity fromDto(TaskDTO d, Long id) {
        BookingEntity booking = bookingService.findById(d.getBookingId());
        if (booking == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        UserEntity cleaner = userRepository.findById(d.getCleanerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cleaner not found"));
        return new TaskEntity(
                id,
                booking,
                cleaner,
                d.getType(),
                com.example.app.task.TaskStatus.valueOf(d.getStatus().name()),
                d.getDue().toLocalDateTime()
        );
    }
}
