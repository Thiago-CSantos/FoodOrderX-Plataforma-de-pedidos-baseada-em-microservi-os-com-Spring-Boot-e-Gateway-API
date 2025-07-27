package com.foodorderx.restaurant.controller;

import com.foodorderx.restaurant.dto.RequestRestaurant;
import com.foodorderx.restaurant.dto.ResponseRestaurant;
import com.foodorderx.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @GetMapping("/")
    public ResponseEntity<List<ResponseRestaurant>> restaurantsList() {
        return ResponseEntity.status(200).body(service.listRestaurant());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRestaurant> detailsRestaurant(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(200).body(service.findById(id));
    }

    //    (ADMIN)
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createRestaurant(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("address") String address,
            @RequestPart(value = "image", required = false) MultipartFile file
    ) {
        if (file == null) {
            return ResponseEntity.status(400).body("Por favor, insira uma imagem valida!");
        }
        service.createRestaurant(new RequestRestaurant(name, description, address, "", true), file);
        return ResponseEntity.status(201).body("Restaurante criado com sucesso");
    }

    //    (ADMIN)
    @PatchMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateRestaurant(
            @PathVariable(value = "id") Long id,
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("address") String address,
            @RequestPart("open") String open,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {
        boolean isOpen = Boolean.parseBoolean(open);
        service.updateRestaurant(id, new RequestRestaurant(name, description, address, "", isOpen), imageFile);
        return ResponseEntity.status(200).body("Atualizado com sucesso!");
    }

    //    (ADMIN)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurants(@PathVariable(value = "id") Long id) {
        service.deleteRestaurant(id);
        return ResponseEntity.status(200).body("Deletado com sucesso "+ new Date());
    }

}
