package com.example.app.task;

import com.example.app.api.TaskApi;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping
public class TaskController implements TaskApi {

  private final TaskService taskService;
  private final BookingService bookingService;
  private final UserRepository userRepository;
  private final TaskRepository taskRepository;

  public TaskController(
      TaskService taskService,
      BookingService bookingService,
      UserRepository userRepository,
      TaskRepository taskRepository) {
    this.taskService = taskService;
    this.bookingService = bookingService;
    this.userRepository = userRepository;
    this.taskRepository = taskRepository;
  }

  @Override
  public ResponseEntity<Void> tasksPost(@Valid @RequestBody TaskDTO taskDTO) {
    BookingEntity booking = bookingService.findById(taskDTO.getBookingId());
    if (booking == null) {
      return ResponseEntity.badRequest().build();
    }
    UserEntity cleaner = userRepository.findById(taskDTO.getCleanerId()).orElse(null);
    if (cleaner == null) {
      return ResponseEntity.badRequest().build();
    }
    TaskEntity entity = fromDto(taskDTO);
    entity.setBooking(booking);
    entity.setCleaner(cleaner);
    entity.setStatus(TaskStatus.PENDING.name());
    taskService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<TaskDTO>> tasksGet(
      @RequestParam(value = "bookingId", required = false) Long bookingId,
      @RequestParam(value = "cleanerId", required = false) Long cleanerId) {
    List<TaskEntity> entities = taskRepository.findAll();
    List<TaskEntity> filtered =
        entities.stream()
            .filter(t -> bookingId == null || (t.getBooking() != null && t.getBooking().getId().equals(bookingId)))
            .filter(t -> cleanerId == null || (t.getCleaner() != null && t.getCleaner().getId().equals(cleanerId)))
            .collect(Collectors.toList());
    List<TaskDTO> dtos = filtered.stream().map(this::toDto).collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
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
  public ResponseEntity<TaskDTO> tasksIdPut(
      @PathVariable("id") Long id, @Valid @RequestBody TaskDTO taskDTO) {
    TaskEntity entity = fromDto(taskDTO);
    entity.setId(id);
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
  }

  private TaskDTO toDto(TaskEntity entity) {
    TaskDTO dto = new TaskDTO();
    dto.setId(entity.getId());
    dto.setBookingId(entity.getBooking() != null ? entity.getBooking().getId() : null);
    dto.setCleanerId(entity.getCleaner() != null ? entity.getCleaner().getId() : null);
    dto.setType(entity.getType());
    dto.setStatus(entity.getStatus() != null ? TaskStatus.valueOf(entity.getStatus()) : null);
    dto.setDue(entity.getDue() != null ? entity.getDue().atOffset(OffsetDateTime.now().getOffset()) : null);
    return dto;
  }

  private TaskEntity fromDto(TaskDTO dto) {
    TaskEntity entity = new TaskEntity();
    entity.setId(dto.getId());
    entity.setType(dto.getType());
    entity.setStatus(dto.getStatus() != null ? dto.getStatus().name() : null);
    entity.setDue(dto.getDue() != null ? dto.getDue().toLocalDateTime() : null);
    return entity;
  }
}
