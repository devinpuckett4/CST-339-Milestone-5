package com.gcu.workspacecst339.service;

import java.util.List;
import java.util.UUID;

import com.gcu.workspacecst339.dto.ProductForm;
import com.gcu.workspacecst339.model.Product;

public interface ProductService {
    Product create(ProductForm form);
    List<Product> getAll();
    boolean deleteById(UUID id);
}