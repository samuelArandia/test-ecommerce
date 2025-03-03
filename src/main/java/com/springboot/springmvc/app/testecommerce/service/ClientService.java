package com.springboot.springmvc.app.testecommerce.service;

import com.springboot.springmvc.app.testecommerce.dto.AuthRequest;
import com.springboot.springmvc.app.testecommerce.dto.RegisterRequest;
import com.springboot.springmvc.app.testecommerce.models.Client;
import com.springboot.springmvc.app.testecommerce.repositories.ClientRepository;
import com.springboot.springmvc.app.testecommerce.security.ApplicationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class ClientService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder, AuthenticationManager  authenticationManager) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void createClient(RegisterRequest request, String role) {
        if (clientRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email ya ha sido registrado");
        }

        Client client = new Client();
        client.setName(request.name());
        client.setEmail(request.email());
        client.setPassword(passwordEncoder.encode(request.password()));
        client.setRole(role);
        clientRepository.save(client);
    }

    public Client authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        return clientRepository.findByEmail(request.email())
                .orElseThrow();
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow();
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }

}
