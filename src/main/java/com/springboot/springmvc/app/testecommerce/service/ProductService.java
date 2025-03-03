package com.springboot.springmvc.app.testecommerce.service;

import com.springboot.springmvc.app.testecommerce.models.Product;
import com.springboot.springmvc.app.testecommerce.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public Product findByName(String name){
        return productRepository.findByName(name).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }
}
