package com.gcu.workspacecst339;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.workspacecst339.dto.RegistrationForm;
import com.gcu.workspacecst339.model.User;
import com.gcu.workspacecst339.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService){ this.userService = userService; }

    // Show the registration page with an empty form
    @GetMapping("/register")
    public String show(Model model){
        model.addAttribute("pageTitle", "Register - CLC App");
        model.addAttribute("form", new RegistrationForm());
        return "register";
    }

    // Handle the registration form submission with Bean Validation
    @PostMapping("/register")
    public String submit(@Valid @ModelAttribute("form") RegistrationForm form,
                         BindingResult result,
                         Model model,
                         HttpSession session,
                         RedirectAttributes ra) {

        // Field-level validations are already handled by @Valid; add custom rules here:

        // Passwords must match
        if (form.getPassword() != null && form.getConfirmPassword() != null
                && !form.getPassword().equals(form.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "password.mismatch", "Passwords must match.");
        }

        // Username must be unique
        if (form.getUsername() != null && userService.usernameTaken(form.getUsername())) {
            result.rejectValue("username", "username.taken", "Username already exists.");
        }

        // If any errors, return to the page to show field errors
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Register - CLC App");
            return "register";
        }

        // Map the form to your User model (since your UserService.register expects a User)
        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setUsername(form.getUsername());
        user.setPassword(form.getPassword());

        // Save the new user and log them in
        userService.register(user);
        session.setAttribute("user", user);

        // Redirect to products with a success message
        ra.addAttribute("success", "Registration successful!");
        return "redirect:/products";
    }
}