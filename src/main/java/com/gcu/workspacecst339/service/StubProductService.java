package com.gcu.workspacecst339.service;

import com.gcu.workspacecst339.dto.ProductForm;
import com.gcu.workspacecst339.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StubProductService implements ProductService {

    private final List<Product> store = new ArrayList<>();

    @Override
    public Product create(ProductForm form) {
        Product p = new Product(
                UUID.randomUUID(),
                form.getName(),
                form.getSku(),
                form.getPrice() == null ? BigDecimal.ZERO : form.getPrice(),
                form.getDescription(),
                form.getCategory(),
                form.isActive()
        );
        store.add(p);
        return p;
    }

    @Override
    public List<Product> getAll() {
        return store;
    }

    @Override
    public boolean deleteById(UUID id) {
        return store.removeIf(p -> p.getId().equals(id));
    }
}