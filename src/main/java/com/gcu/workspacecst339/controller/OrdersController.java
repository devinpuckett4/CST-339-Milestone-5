package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {

    @GetMapping("/orders")
    public String list(Model model) {
        // TODO: when you have a service: model.addAttribute("orders", orderService.listNewestFirst());
        return "orders";
    }
}