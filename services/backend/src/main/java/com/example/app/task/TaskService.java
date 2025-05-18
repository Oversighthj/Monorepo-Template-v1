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

  public TaskEntity findById(Long id) {
    Optional<TaskEntity> opt = taskRepository.findById(id);
    return opt.orElse(null);
  }

  public List<TaskEntity> findAll() {
    return taskRepository.findAll();
  }

  public TaskEntity update(TaskEntity task) {
    return taskRepository.save(task);
  }

  public void delete(Long id) {
    taskRepository.deleteById(id);
  }
}
