package com.example.SQLDemo.service;

import com.example.SQLDemo.entity.Product;
import com.example.SQLDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }
    public List<Product> getProducts(){
        return productRepository.findAll();
    }
    public Product getById(int id){
        return productRepository.findById(id).orElse(null);
    }
    public List<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }
    public String deleteProduct(int id){
        productRepository.deleteById(id);
        return "Product Removed "+id;
    }
    public Product updateProduct(Product product){
        Product existing_product = productRepository.findById(product.getId()).orElse(null);
        if(existing_product!=null){
            existing_product.setName(product.getName());
            existing_product.setPrice(product.getPrice());
            existing_product.setQuantity(product.getQuantity());
            productRepository.save(existing_product);
        }
        return existing_product;
    }
}
