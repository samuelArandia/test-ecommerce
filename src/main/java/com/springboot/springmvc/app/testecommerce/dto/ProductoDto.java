package com.springboot.springmvc.app.testecommerce.dto;


public record ProductoDto(
    Long id,
    String name,
    Double price,
    Integer stock
) {
}

