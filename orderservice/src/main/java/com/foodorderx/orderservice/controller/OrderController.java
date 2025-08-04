package com.foodorderx.orderservice.controller;

import com.foodorderx.orderservice.dto.RequestCreate;
import com.foodorderx.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/user/{userId}")
    public ResponseEntity<String> listOrderUser(@PathVariable("userId") String userId) {
        return ResponseEntity.status(200).body("Hello World!");
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<String> listOrderRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        return ResponseEntity.status(200).body("Hello World!");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<String> detailsOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(200).body("Hello World!");
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrders(@RequestBody RequestCreate body) {
        service.createOrder(body);
        return ResponseEntity.status(201).body("Pedido criado com sucesso!");
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<String> updateStatus(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(200).body("Hello World!");
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.status(200).body("Hello World!");
    }

}
