package com.springboot.springmvc.app.testecommerce.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Pedido no encontrado con ID: " + id);
    }
}