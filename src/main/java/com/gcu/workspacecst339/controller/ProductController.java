package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.workspacecst339.business.ProductService;
import com.gcu.workspacecst339.dto.ProductForm;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // GET /products
    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.listNewestFirst());
        return "products";
    }

    // GET create form
    @GetMapping("/products/create")
    public String createForm(ProductForm form) {
        return "product-create";
    }

    // POST create and redirect
    @PostMapping("/products/create")
    public String create(@Valid ProductForm form, BindingResult br) {
        if (br.hasErrors()) return "product-create";
        productService.create(form.getName(), form.getPrice(), form.getDescription());
        return "redirect:/products";
    }

    // POST delete and redirect
    @PostMapping("/products/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}