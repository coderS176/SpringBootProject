package com.example.SQLDemo.controller;

import com.example.SQLDemo.entity.Product;
import com.example.SQLDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

   // create a new product
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    // create multiple products
    @PostMapping("/batch")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);
    }

    //retrieve all products
    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    //retrieve a product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getById(id);
    }

   // search for products by name
    @GetMapping("/search")
    public List<Product> getProductByName(@RequestParam String name){
        return productService.getProductByName(name);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    //Delete an existing product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
}



/*
*
*  API endpoints typically described using a consistent naming convention and structure.
* This approach often adheres to restful principles and commonly used conventions for URL paths, HTTP methods, and
* parameter naming.

Create Single Product: POST /products
Create Multiple Products: POST /products/batch
Retrieve All Products: GET /products
Retrieve Product by ID: GET /products/{id}
Search Products by Name: GET /products/search?name={name}
Update Product: PUT /products/{id}
Delete Product: DELETE /products/{id}
* */