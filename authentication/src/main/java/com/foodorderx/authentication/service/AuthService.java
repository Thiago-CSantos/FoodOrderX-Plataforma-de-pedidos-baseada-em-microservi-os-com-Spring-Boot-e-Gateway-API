package com.foodorderx.authentication.service;

import com.foodorderx.authentication.dto.AuthRequest;
import com.foodorderx.authentication.dto.AuthResponse;
import com.foodorderx.authentication.dto.RegisterRequest;
import com.foodorderx.authentication.entity.User;
import com.foodorderx.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;


    public List<User> listUser() {
        return repository.findAll();

    }

    // Registro de novo usuário
    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        String role = "USER";
        if (request.email().equalsIgnoreCase("admin@foodorderx.com")) {
            role = "ADMIN";
        }

        user.setRole(role);

        repository.save(user);

        repository.save(user);
        return new AuthResponse(jwtService.generateToken(user));
    }

    // Autenticação (login)
    public AuthResponse login(AuthRequest request) {
        // Valida credenciais
        var a = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        System.out.println(a.toString());

        // Busca usuário e gera token
        var user = repository.findByEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new AuthResponse(jwtService.generateToken(user));
    }

}
