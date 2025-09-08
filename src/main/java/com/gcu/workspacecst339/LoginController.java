package com.gcu.workspacecst339;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.workspacecst339.model.LoginForm;
import com.gcu.workspacecst339.model.User;
import com.gcu.workspacecst339.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    // Show the login page with an empty form
    @GetMapping("/login")
    public String show(Model model) {
        model.addAttribute("pageTitle", "Login - CLC App");
        // IMPORTANT: name it "form" to match th:object="${form}" in login.html
        model.addAttribute("form", new LoginForm());
        return "login";
    }

    // Process the submitted login form using Bean Validation + BindingResult
    @PostMapping("/login")
    public String submit(@Valid @ModelAttribute("form") LoginForm form,
                         BindingResult result,
                         Model model,
                         HttpSession session,
                         RedirectAttributes ra) {

        // If field validation failed, re-render the page with field errors
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Login - CLC App");
            return "login";
        }

        // Authenticate using your service
        User u = userService.authenticate(form.getUsername(), form.getPassword());

        if (u == null) {
            result.reject("login.failed", "Invalid username or password.");
            model.addAttribute("pageTitle", "Login - CLC App");
            return "login";
        }

        // Success: store user in session and redirect with a success alert
        session.setAttribute("user", u);
        ra.addAttribute("success", "Welcome, " + form.getUsername() + "!");
        return "redirect:/";
    }

    // Clear the session and return to the home page
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}