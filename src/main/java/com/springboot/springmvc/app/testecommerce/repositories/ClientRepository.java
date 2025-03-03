package com.springboot.springmvc.app.testecommerce.repositories;

import com.springboot.springmvc.app.testecommerce.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);



    boolean existsByEmail(String email);
}
