package com.gcu.workspacecst339.data;

import org.springframework.data.repository.CrudRepository;
import com.gcu.workspacecst339.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {}


