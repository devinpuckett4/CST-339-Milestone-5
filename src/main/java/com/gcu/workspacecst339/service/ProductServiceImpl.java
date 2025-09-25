package com.gcu.workspacecst339.service;

import org.springframework.stereotype.Service;
import com.gcu.workspacecst339.data.ProductRepository;
import com.gcu.workspacecst339.model.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Product> listAll() {
        return repo.findAll();
    }

    @Override
    public Product getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Product update(Product p) {
        if (p.getName() == null || p.getName().trim().isEmpty()) return null;

        BigDecimal price = p.getPrice();
        if (price != null && price.compareTo(BigDecimal.ZERO) < 0) return null;

        Integer qty = p.getQuantity();
        if (qty != null && qty < 0) return null;

        if (p.getCreatedAt() == null) {
            p.setCreatedAt(LocalDateTime.now());
        }

        return repo.save(p);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}