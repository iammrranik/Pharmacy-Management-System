package com.pharmacy.management.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public (no token required)
                .requestMatchers("/api/auth/**").permitAll()

                // Public - GET medicines and suppliers
                .requestMatchers(HttpMethod.GET, "/api/medicines/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/suppliers/**").permitAll()

                // Users - ADMIN only: listing, searching, creating
                .requestMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/users/page").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/users/phone/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/users/username/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/users/count").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/phone/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/username/**").hasRole("ADMIN")

                // Users - both roles for individual RUD (self-check in controller)
                .requestMatchers(HttpMethod.GET, "/api/users/*").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/users/*").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/users/*").authenticated()

                // ADMIN / CUSTOMER (both roles)
                .requestMatchers(HttpMethod.GET, "/api/orders/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/orders/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/order-details/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/order-details/**").authenticated()

                // ADMIN only - Medicines
                .requestMatchers(HttpMethod.POST, "/api/medicines/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/medicines/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/medicines/**").hasRole("ADMIN")

                // ADMIN only - Suppliers
                .requestMatchers(HttpMethod.POST, "/api/suppliers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/suppliers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/suppliers/**").hasRole("ADMIN")

                // ADMIN only - Orders
                .requestMatchers(HttpMethod.PUT, "/api/orders/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("ADMIN")

                // ADMIN only - Order Details
                .requestMatchers(HttpMethod.PUT, "/api/order-details/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order-details/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
