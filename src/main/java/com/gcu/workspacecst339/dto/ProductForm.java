package com.gcu.workspacecst339.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProductForm {

    @NotBlank(message = "{NotBlank.productForm.name}")
    private String name;

    @NotBlank(message = "{NotBlank.productForm.sku}")
    @Pattern(regexp = "^[A-Z0-9_-]{4,20}$", message = "{Pattern.productForm.sku}")
    private String sku;

    @NotNull(message = "{NotNull.productForm.price}")
    @DecimalMin(value = "0.01", message = "{DecimalMin.productForm.price}")
    private BigDecimal price;

    @Size(max = 500, message = "{Size.productForm.description}")
    private String description;

    @NotBlank(message = "{NotBlank.productForm.category}")
    private String category;

    private boolean active = true;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}