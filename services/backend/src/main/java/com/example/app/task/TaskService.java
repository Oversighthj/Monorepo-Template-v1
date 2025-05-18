package com.example.app.task;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskEntity create(TaskEntity task) {
    return taskRepository.save(task);
  }

  public List<TaskEntity> findAll() {
    return taskRepository.findAll();
  }

  public TaskEntity findById(Long id) {
    Optional<TaskEntity> entity = taskRepository.findById(id);
    return entity.orElse(null);
  }

  public TaskEntity update(TaskEntity task) {
    if (task.getId() == null) {
      throw new IllegalArgumentException("task id required");
    }
    return taskRepository.save(task);
  }

  public void delete(Long id) {
    taskRepository.deleteById(id);
  }
}
