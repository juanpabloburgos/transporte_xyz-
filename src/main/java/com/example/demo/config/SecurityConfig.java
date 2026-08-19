package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .httpBasic(httpBasic -> {})
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(HttpMethod.POST, "/api/camiones")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/camiones/**")
                .hasAnyRole("ADMIN", "SUPERVISOR")

                .requestMatchers(HttpMethod.PUT, "/api/camiones/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/api/camiones/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/conductores")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/conductores/**")
                .hasAnyRole("ADMIN", "SUPERVISOR")

                .requestMatchers(HttpMethod.PUT, "/api/conductores/**")
                .hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/api/conductores/**")
                .hasRole("ADMIN")

                .requestMatchers("/api/asociaciones/**")
                .hasRole("SUPERVISOR")

                .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        var admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        var supervisor = User.builder()
                .username("supervisor")
                .password(encoder.encode("super123"))
                .roles("SUPERVISOR")
                .build();

        return new InMemoryUserDetailsManager(admin, supervisor);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}