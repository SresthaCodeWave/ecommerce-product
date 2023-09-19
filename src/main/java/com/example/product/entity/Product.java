package com.example.product.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document
public class Product {
    @Id
    private String productId;
    private String productName;
    private String brand;
    private String description;
    private String imgSrc;
    private double price;
}
