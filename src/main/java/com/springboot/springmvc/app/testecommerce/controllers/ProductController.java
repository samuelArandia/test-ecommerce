package com.springboot.springmvc.app.testecommerce.controllers;

import com.springboot.springmvc.app.testecommerce.models.Product;
import com.springboot.springmvc.app.testecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@Tag(name = "Product", description = "Endpoints de productos")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @Operation(summary = "Obtener todos los productos", description = "Obtiene todos los productos registrados en el sistema")
    @GetMapping ("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.findAll());
    }


    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto en el sistema")
    @PostMapping("/createProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @Operation(summary = "Obtener producto por ID", description = "Obtiene un producto por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @Operation(summary = "Eliminar producto por ID", description = "Elimina un producto por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return ResponseEntity.ok("Producto eliminado exitosamente");
    }
}
