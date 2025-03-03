package com.springboot.springmvc.app.testecommerce.exceptions;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String status) {
        super("Estado inválido: " + status);
    }
}