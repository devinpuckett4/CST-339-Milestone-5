package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.workspacecst339.business.UserService;
import com.gcu.workspacecst339.dto.LoginForm;

import jakarta.validation.Valid;

@Controller
// Login page + submit
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // GET /login
    @GetMapping("/login")
    public String login(LoginForm form) {
        return "login";
    }

    // POST /login and redirect or show error
    @PostMapping("/login")
    public String doLogin(@Valid LoginForm form, BindingResult br, Model model) {
        if (br.hasErrors()) return "login";

        return userService.authenticate(form.getUsername(), form.getPassword())
                .map(u -> "redirect:/products")
                .orElseGet(() -> {
                    model.addAttribute("loginError", "Invalid username or password.");
                    return "login";
                });
    }
}