package com.springboot.springmvc.app.testecommerce.controllers;

import com.springboot.springmvc.app.testecommerce.exceptions.*;
import com.springboot.springmvc.app.testecommerce.models.Order;
import com.springboot.springmvc.app.testecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.springboot.springmvc.app.testecommerce.exceptions.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
@Tag(name = "Order", description = "Endpoints de pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Crear un pedido", description = "Crea un nuevo pedido en el sistema")
    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService.createOrder(order);
            return ResponseEntity.ok(
                    new SuccessResponse("ORDER_CREATED", "Pedido creado exitosamente"));
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("CLIENT_NOT_FOUND", e.getMessage()));
        } catch (InvalidOrderException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("INVALID_ORDER_DATA", e.getMessage()));
        }
    }

    @Operation(summary = "Obtener todos los pedidos", description = "Obtiene todos los pedidos registrados en el sistema")
    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @Operation(summary = "Obtener pedido por ID", description = "Obtiene un pedido por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.findById(id);
            return ResponseEntity.ok(order);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("ORDER_NOT_FOUND", "Pedido no encontrado con ID: " + id));
        }
    }

    @Operation(summary = "Obtener pedidos por cliente", description = "Obtiene todos los pedidos de un cliente")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getOrdersByClient(@PathVariable Long clientId) {
        try {
            return ResponseEntity.ok(orderService.findByClient(clientId));
        } catch (ClientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("CLIENT_NOT_FOUND", "Cliente no encontrado con ID: " + clientId));
        }
    }

    @Operation(summary = "Actualizar estado de pedido", description = "Actualiza el estado de un pedido")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestParam String newStatus) {
        try {
            Order updatedOrder = orderService.updateOrderStatus(id, newStatus);
            return ResponseEntity.ok(updatedOrder);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("ORDER_NOT_FOUND", e.getMessage()));
        } catch (InvalidStatusException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("INVALID_STATUS", e.getMessage()));
        }
    }

    @Operation(summary = "Cancelar pedido", description = "Cancela un pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {
        try {
            orderService.cancelOrder(id);
            return ResponseEntity.ok(
                    new SuccessResponse("ORDER_CANCELLED", "Pedido cancelado exitosamente"));
        } catch (OrderNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("ORDER_NOT_FOUND", e.getMessage()));
        }
    }
}