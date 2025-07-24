package com.foodorderx.authentication.controller;

import com.foodorderx.authentication.dto.AuthRequest;
import com.foodorderx.authentication.dto.AuthResponse;
import com.foodorderx.authentication.dto.RegisterRequest;
import com.foodorderx.authentication.entity.User;
import com.foodorderx.authentication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @GetMapping("/hello")
    public ResponseEntity<List<User>> helloWord() {
        List<User> userList = service.listUser();
        return ResponseEntity.status(200).body(userList);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(201).body(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
