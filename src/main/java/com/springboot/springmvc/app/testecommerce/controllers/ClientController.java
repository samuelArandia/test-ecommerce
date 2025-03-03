package com.springboot.springmvc.app.testecommerce.controllers;

import com.springboot.springmvc.app.testecommerce.models.Client;
import com.springboot.springmvc.app.testecommerce.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
@Slf4j
@Tag(name = "Client", description = "Endpoints de clientes")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @Operation(summary = "Obtener todos los clientes", description = "Obtiene todos los clientes registrados en el sistema")
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllClients(){
        try {
            return ResponseEntity.ok(clientService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @Operation(summary = "Obtener cliente por email", description = "Obtiene un cliente por su email")
    @GetMapping("/findClientByEmail")
    public ResponseEntity<Client> getClientById(String email){
        try {
            return ResponseEntity.ok(clientService.findByEmail(email));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
