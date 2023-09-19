package com.example.product.services;

import com.example.product.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public void addProduct(Product product);
    public void deleteProduct(String productId);
    public Iterable<Product> findAllProducts();
    public Optional<Product> findById(String productId);
    public Optional<List<Product>> findByName(String productName);

}
