package com.gcu.workspacecst339;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.workspacecst339.dto.ProductForm;
import com.gcu.workspacecst339.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Single view: form + list
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("pageTitle", "Products - CLC App");
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new ProductForm());
        }
        model.addAttribute("products", productService.getAll());
        return "products";
    }

    // Alias so /products/create lands on the same page
    @GetMapping("/products/create")
    public String createAlias() {
        return "redirect:/products";
    }

    @PostMapping("/products/create")
    public String create(@Valid @ModelAttribute("form") ProductForm form,
                         BindingResult result,
                         RedirectAttributes ra) {
        if (result.hasErrors()) {
            ra.addFlashAttribute("org.springframework.validation.BindingResult.form", result);
            ra.addFlashAttribute("form", form);
            return "redirect:/products";
        }
        productService.create(form);
        ra.addAttribute("success", "Product created successfully!");
        return "redirect:/products";
    }

    // delete handler (in-memory)
    @PostMapping("/products/delete/{id}")
    public String delete(@PathVariable("id") UUID id, RedirectAttributes ra) {
        boolean ok = productService.deleteById(id);
        ra.addAttribute(ok ? "success" : "error", ok ? "Product deleted." : "Could not delete product.");
        return "redirect:/products";
    }
}