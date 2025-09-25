package com.gcu.workspacecst339.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.workspacecst339.model.Product;
import com.gcu.workspacecst339.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // list page
    @GetMapping("/products")
    public String list(Model model) {
        model.addAttribute("products", productService.listAll());
        return "products";              // templates/products.html
    }

    // edit page
    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Product p = productService.getById(id);
        if (p == null) return "redirect:/products";
        model.addAttribute("product", p);
        return "product-edit";          // templates/product-edit.html
    }

    // save changes from edit page
    @PostMapping("/products/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        productService.update(product);
        return "redirect:/products";
    }

    // delete button
    @PostMapping("/products/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }

    // show empty form
    @GetMapping("/products/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-create";        // templates/product-create.html
    }

    // handle submit
    @PostMapping("/products")
    public String create(@ModelAttribute Product p) {
        productService.update(p);
        return "redirect:/products";
    }
}