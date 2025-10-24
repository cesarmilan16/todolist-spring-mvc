package com.app.todolist.repository;

import com.app.todolist.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdOrderByIdDesc(Long userId);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
