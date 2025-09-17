package com.gcu.workspacecst339.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Product entity mapped to the "products" table (Spring Data JDBC).
 */
@Table("products")
public class Product {

    /** Primary key. */
    @Id
    private Long id;

    /** Product name. */
    private String name;

    /** Short description. */
    private String description;

    /** Price  */
    private BigDecimal price;

    /** Timestamp when the row was created  */
    @Column("created_at")
    private LocalDateTime createdAt;

    /** No-arg constructor  */
    public Product() {}

    /** Convenience constructor. */
    public Product(Long id, String name, BigDecimal price, String description, LocalDateTime createdAt) {
        this.id = id; this.name = name; this.price = price; this.description = description; this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}