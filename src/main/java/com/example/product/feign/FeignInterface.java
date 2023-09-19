package com.example.product.feign;

import com.example.product.dto.ProductDTO;
import com.example.product.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@FeignClient(url = "http://localhost:8081/merchant",name = "abc")
public interface FeignInterface {
    @PostMapping("/addProductByMerchant")
    public ResponseEntity<Product> addProductByMerchant();
    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<Product>> getAll(ProductDTO productDTO);
    @GetMapping("getProductPrice/{productId}")
    public Integer getProductPrice(@PathVariable("productId") String productId);


    }
