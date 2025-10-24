package com.app.todolist.services;

import com.app.todolist.models.Task;
import com.app.todolist.models.User;
import com.app.todolist.repository.TaskRepository;
import com.app.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findByUserIdOrderByIdDesc(userId);
    }

    public void createTask(Long userId, String title) {
        User user = userRepository.findById(userId).orElseThrow();
        Task task = new Task();
        task.setTitle(title);
        task.setUser(user);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long userId, Long taskId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId).orElseThrow();
        taskRepository.delete(task);
    }

    public void toggleTask(Long userId, Long taskId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId).orElseThrow();
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
