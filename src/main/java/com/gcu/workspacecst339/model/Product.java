package com.gcu.workspacecst339.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String sku;
    private BigDecimal price;
    private String description;
    private String category;
    private boolean active;

    public Product() {}

    public Product(UUID id, String name, String sku, BigDecimal price,
                   String description, String category, boolean active) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.description = description;
        this.category = category;
        this.active = active;
    }

    // getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
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