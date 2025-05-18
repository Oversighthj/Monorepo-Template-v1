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

    /* ---------- CRUD wrappers ---------- */

    public TaskEntity create(TaskEntity task) {
        return taskRepository.save(task);
    }

    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    public TaskEntity findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public TaskEntity update(TaskEntity task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("task id required");
        }

        return taskRepository.findById(task.getId())
                .map(existing -> {
                    existing.setBooking(task.getBooking());
                    existing.setCleaner(task.getCleaner());
                    existing.setType(task.getType());
                    existing.setStatus(task.getStatus());
                    existing.setDue(task.getDue());
                    return taskRepository.save(existing);
                })
                .orElse(null);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
