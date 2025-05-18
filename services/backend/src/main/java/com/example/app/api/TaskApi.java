package com.example.app.api;

import com.example.app.task.TaskDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.validation.Valid;

public interface TaskApi {

  @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = {"application/json"})
  ResponseEntity<Void> tasksPost(@Valid @RequestBody TaskDTO taskDTO);

  @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces = {"application/json"})
  ResponseEntity<List<TaskDTO>> tasksGet(
      @RequestParam(value = "bookingId", required = false) Long bookingId,
      @RequestParam(value = "cleanerId", required = false) Long cleanerId);

  @RequestMapping(method = RequestMethod.GET, value = "/tasks/{id}", produces = {"application/json"})
  ResponseEntity<TaskDTO> tasksIdGet(@PathVariable("id") Long id);

  @RequestMapping(method = RequestMethod.PUT, value = "/tasks/{id}", consumes = {"application/json"}, produces = {"application/json"})
  ResponseEntity<TaskDTO> tasksIdPut(
      @PathVariable("id") Long id,
      @Valid @RequestBody TaskDTO taskDTO);

  @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{id}")
  ResponseEntity<Void> tasksIdDelete(@PathVariable("id") Long id);
}
