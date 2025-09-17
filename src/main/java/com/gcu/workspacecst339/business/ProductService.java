package com.gcu.workspacecst339.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.workspacecst339.data.ProductRepository;
import com.gcu.workspacecst339.model.Product;

@Service
public class ProductService {
    private final ProductRepository products;

    public ProductService(ProductRepository products) {
        this.products = products;
    }

    public Product create(String name, BigDecimal price, String description) {
        Product p = new Product(null, name, price, description, null); // created_at set by DB
        return products.save(p);
    }

    public List<Product> listNewestFirst() {
        List<Product> list = new ArrayList<>();
        products.findAll().forEach(list::add);
        list.sort(Comparator.comparing(Product::getCreatedAt,
                Comparator.nullsLast(Comparator.naturalOrder())).reversed());
        return list;
    }

    // Alias if a controller calls getAll()
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        products.findAll().forEach(list::add);
        return list;
    }

    public void deleteById(Long id) {
        products.deleteById(id);
    }
}
