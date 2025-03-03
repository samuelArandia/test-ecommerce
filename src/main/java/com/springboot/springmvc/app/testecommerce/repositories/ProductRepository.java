package com.springboot.springmvc.app.testecommerce.repositories;

import com.springboot.springmvc.app.testecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    Optional<Product> findByName(String name);
    Optional<Product> findByStock(Integer stock);
    Optional<Product> findByPrice(Double price);
}
