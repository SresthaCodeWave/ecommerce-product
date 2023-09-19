package com.example.product.services.impl;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addProduct(Product product) {
        if(product.getProductId()==null){
            product.setProductId(UUID.randomUUID().toString());
            productRepository.insert(product);
        }
        else{
            productRepository.save(product);
        }

//        productRepository.save(product);
    }

    public Iterable<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(String productId) {
        return  productRepository.findById(productId);
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);

    }
    public Optional<List<Product>> findByName(String productName){
        Optional<List<Product>> optionalProducts=productRepository.findByProductName(productName);
        return optionalProducts;
    }
//    public Boolean presentOrNot(String productId){
//        productRepository.existsById(productId);
//    }
}
