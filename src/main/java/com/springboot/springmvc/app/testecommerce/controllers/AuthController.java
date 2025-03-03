package com.springboot.springmvc.app.testecommerce.controllers;


import com.springboot.springmvc.app.testecommerce.dto.AuthRequest;
import com.springboot.springmvc.app.testecommerce.dto.RegisterRequest;
import com.springboot.springmvc.app.testecommerce.models.Client;
import com.springboot.springmvc.app.testecommerce.service.ClientService;
import com.springboot.springmvc.app.testecommerce.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@Tag(name = "Auth", description = "Endpoints de autenticación")
public class AuthController {
    private final ClientService clientService;
    private final JwtService jwtService;

    public AuthController(ClientService clientService, JwtService jwtService) {
        this.clientService = clientService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Registro de usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al registrar usuario",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            clientService.createClient(request, "USER");
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario:" + e.getMessage());
        }
    }

    @Operation(summary = "Autenticación de usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario autenticado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Credenciales inválidas",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request) {
        try {
            Client client = clientService.authenticate(request);
            String jwtToken = jwtService.generateToken(client);
            return ResponseEntity.ok( "Bienvenido " +
                    client.getName() + " autenticado exitosamente. Token: " + jwtToken
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Credenciales inválidas");
        }
    }

    @Operation(summary = "Registro de administrador", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al registrar usuario",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest request) {
        try {
            clientService.createClient(request , "ADMIN");
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar usuario:" + e.getMessage());
        }
    }
}