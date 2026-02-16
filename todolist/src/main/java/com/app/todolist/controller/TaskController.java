package com.app.todolist.controller;

import com.app.todolist.services.TaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        model.addAttribute("tasks", taskService.getAllTasks(userId));
        model.addAttribute("userLogin", session.getAttribute("userLogin"));
        return "tasks";
    }

    @PostMapping
    public String createTasks(@RequestParam String title, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        taskService.createTask(userId, title);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTasks(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        taskService.deleteTask(userId, id);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/toggle")
    public String toggleTasks(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";
        taskService.toggleTask(userId, id);
        return "redirect:/tasks";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
