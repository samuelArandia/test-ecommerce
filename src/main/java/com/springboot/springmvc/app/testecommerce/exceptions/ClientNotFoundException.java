package com.springboot.springmvc.app.testecommerce.exceptions;

import lombok.Getter;

@Getter
public class ClientNotFoundException extends RuntimeException {
    private final String errorCode; // Código del error

    public ClientNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}