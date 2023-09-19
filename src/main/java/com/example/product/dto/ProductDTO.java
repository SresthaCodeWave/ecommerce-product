package com.example.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productId;
    private String productName;
    private String brand;
    private String description;
    private String imgSrc;
    private double price;

}
