package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.workspacecst339.business.UserService;
import com.gcu.workspacecst339.dto.RegistrationForm;

import jakarta.validation.Valid;

@Controller
// Handles register page display and submission
public class RegistrationController {

    private final UserService userService;

    // DI for user operations
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // GET /register  show registration form
    @GetMapping("/register")
    public String register(RegistrationForm form) {
        return "register";
    }

    // POST /register to validate, create user, redirect on success
    @PostMapping("/register")
    public String doRegister(@Valid RegistrationForm form, BindingResult br, Model model) {
        // Bean Validation errors then re-render form
        if (br.hasErrors()) return "register";

        // Require matching passwords
        if (form.getConfirmPassword() != null && !form.getPassword().equals(form.getConfirmPassword())) {
            model.addAttribute("registerError", "Passwords do not match.");
            return "register";
        }

        // Enforce unique username
        if (userService.usernameTaken(form.getUsername())) {
            model.addAttribute("registerError", "Username is already taken.");
            return "register";
        }

        // Create user
        userService.register(
                form.getUsername(),
                form.getPassword(),
                form.getFirstName(),
                form.getLastName(),
                form.getEmail()
        );

        // Success go to products list
        return "redirect:/products";
    }
}