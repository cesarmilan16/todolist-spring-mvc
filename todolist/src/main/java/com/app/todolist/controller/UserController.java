package com.app.todolist.controller;

import com.app.todolist.models.User;
import com.app.todolist.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        System.out.println("register request: " + user);
        User registerdUser = userService.registerUser(user.getLogin(), user.getPassword(), user.getEmail());
        if (registerdUser == null) {
            model.addAttribute("errorMessage", "Invalid username");
            return "error";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") User user, HttpSession session , Model model) {
        // 1. Spring crea User u con los datos del formulario
        // 2. user.getLogin() → contiene el username del input
        // 3. user.getPassword() → contiene el password del input

        User authenticated = userService.authenticate(user.getLogin(), user.getPassword());
        System.out.println(authenticated);

        if (authenticated == null) {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "error";
        }

        session.setAttribute("userId", authenticated.getId()); // Para lógica
        session.setAttribute("userLogin", authenticated.getLogin()); // Para mostrar en UI
        return "redirect:/tasks";
    }

}
