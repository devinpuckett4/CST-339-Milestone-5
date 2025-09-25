package com.gcu.workspacecst339.service;

import com.gcu.workspacecst339.model.Product;

public interface ProductService {
    Iterable<Product> listAll();
    Product getById(Long id);
    Product update(Product p);
    void delete(Long id);
}
