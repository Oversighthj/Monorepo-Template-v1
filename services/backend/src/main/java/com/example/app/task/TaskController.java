package com.example.app.task;

import com.example.app.api.TaskApi;
import com.example.app.booking.BookingEntity;
import com.example.app.booking.BookingService;
import com.example.app.model.TaskDTO;
import com.example.app.model.TaskStatus;
import com.example.app.user.UserEntity;
import com.example.app.user.UserRepository;
import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TaskController implements TaskApi {

  private final TaskService taskService;
  private final BookingService bookingService;
  private final UserRepository userRepository;

  public TaskController(TaskService taskService, BookingService bookingService, UserRepository userRepository) {
    this.taskService = taskService;
    this.bookingService = bookingService;
    this.userRepository = userRepository;
  }

  @Override
  public ResponseEntity<Void> tasksPost(@Valid @RequestBody TaskDTO taskDTO) {
    BookingEntity booking = bookingService.findById(taskDTO.getBookingId());
    UserEntity cleaner = userRepository.findById(taskDTO.getCleanerId()).orElse(null);
    if (booking == null || cleaner == null) {
      return ResponseEntity.badRequest().build();
    }
    TaskEntity entity = new TaskEntity();
    entity.setBooking(booking);
    entity.setCleaner(cleaner);
    entity.setType(taskDTO.getType());
    entity.setStatus(TaskStatus.PENDING.name());
    entity.setDue(taskDTO.getDue() != null ? taskDTO.getDue().toLocalDateTime() : null);
    taskService.create(entity);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Override
  public ResponseEntity<List<TaskDTO>> tasksGet(@RequestParam(value = "bookingId", required = false) Long bookingId,
                                               @RequestParam(value = "cleanerId", required = false) Long cleanerId) {
    List<TaskEntity> all = taskService.findAll();
    List<TaskEntity> filtered = all.stream()
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
  public ResponseEntity<TaskDTO> tasksIdPut(@PathVariable("id") Long id, @Valid @RequestBody TaskDTO taskDTO) {
    TaskEntity existing = taskService.findById(id);
    if (existing == null) {
      return ResponseEntity.notFound().build();
    }
    if (taskDTO.getBookingId() != null) {
      BookingEntity booking = bookingService.findById(taskDTO.getBookingId());
      if (booking == null) {
        return ResponseEntity.badRequest().build();
      }
      existing.setBooking(booking);
    }
    if (taskDTO.getCleanerId() != null) {
      UserEntity cleaner = userRepository.findById(taskDTO.getCleanerId()).orElse(null);
      if (cleaner == null) {
        return ResponseEntity.badRequest().build();
      }
      existing.setCleaner(cleaner);
    }
    if (taskDTO.getType() != null) existing.setType(taskDTO.getType());
    if (taskDTO.getStatus() != null) existing.setStatus(taskDTO.getStatus().name());
    if (taskDTO.getDue() != null) existing.setDue(taskDTO.getDue().toLocalDateTime());
    TaskEntity saved = taskService.update(existing);
    return ResponseEntity.ok(toDto(saved));
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
}
