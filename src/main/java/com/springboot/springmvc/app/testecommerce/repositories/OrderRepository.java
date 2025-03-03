package com.springboot.springmvc.app.testecommerce.repositories;

import com.springboot.springmvc.app.testecommerce.models.Client;
import com.springboot.springmvc.app.testecommerce.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(Long clientId);
    Optional<Order> findById(Long id);
    List<Order> findAll();
}
