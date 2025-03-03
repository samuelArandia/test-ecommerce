package com.springboot.springmvc.app.testecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.springboot.springmvc.app.testecommerce.security.ApplicationConfig;
import com.springboot.springmvc.app.testecommerce.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Import({JwtAuthenticationFilter.class, ApplicationConfig.class})
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final ApplicationConfig applicationConfig;

    // Constructor que recibe las dependencias
    public SecurityConfig(
            @Lazy JwtAuthenticationFilter jwtAuthFilter,
            ApplicationConfig applicationConfig
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.applicationConfig = applicationConfig;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/products/**").permitAll()
                        .requestMatchers("/orders/**").authenticated()
                        .requestMatchers("/clients/**").authenticated()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(applicationConfig.authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}