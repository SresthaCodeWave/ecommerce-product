package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.dto.ProductPriceDTO;
import com.example.product.entity.Product;
import com.example.product.feign.FeignInterface;
import com.example.product.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    FeignInterface feignInterface;
    @GetMapping("/allProducts")
    public ResponseEntity<ArrayList<Product>> getAllProducts(){
        ArrayList<Product>   productsArrayList=new ArrayList<Product>();
        Iterable<Product> productIterator=productService.findAllProducts();
        for(Product product:productIterator){
            productsArrayList.add(product);
        }
        return new ResponseEntity<ArrayList<Product>>(productsArrayList,HttpStatus.OK);
    }
    @GetMapping("/allProductsWithPrice")
    public ResponseEntity<ArrayList<ProductPriceDTO>> getAllProductsWithPrices(){
        ArrayList<ProductPriceDTO> productPriceDTOArrayList=new ArrayList<ProductPriceDTO>();
        Iterable<Product> productIterable=productService.findAllProducts();
        for(Product product:productIterable){
            ProductPriceDTO productPriceDTO=new ProductPriceDTO();
            productPriceDTO.setBrand(product.getBrand());
            productPriceDTO.setDescription(product.getDescription());
            productPriceDTO.setImgSrc(product.getImgSrc());
            productPriceDTO.setProductId(product.getProductId());
            productPriceDTO.setProductName(product.getProductName());
            productPriceDTO.setProductPrice(feignInterface.getProductPrice(product.getProductId()));
            productPriceDTOArrayList.add(productPriceDTO);
        }
return new ResponseEntity<ArrayList<ProductPriceDTO>>(productPriceDTOArrayList,HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDTO productDTO)
    {
        Product product=new Product();
        BeanUtils.copyProperties(productDTO,product);
        productService.addProduct(product);
        return new ResponseEntity<String>(product.getProductId(),HttpStatus.CREATED);
    }
    @GetMapping("/findById/{productId}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("productId") String productId) {
        Optional<Product> product = productService.findById(productId);
        ProductDTO productDTO=new ProductDTO();
        BeanUtils.copyProperties(product.get(), productDTO);
        return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
    }
    @GetMapping("/findByProductName/{productName}")
    public ResponseEntity<List<Product>> findByName(@PathVariable("productName") String productName){
        Optional<List<Product>> optionalProducts=productService.findByName(productName);
        List<Product> productsList=optionalProducts.get();
        return new ResponseEntity<List<Product>>(productsList,HttpStatus.OK);
    }


}
