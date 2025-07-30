package com.foodorderx.restaurant.controller;

import com.foodorderx.restaurant.dto.RequestMenuItem;
import com.foodorderx.restaurant.dto.ResponseMenuItem;
import com.foodorderx.restaurant.dto.ResponseRestaurantWithMenuDTO;
import com.foodorderx.restaurant.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuItemController {

    @Autowired
    MenuItemService service;

    @GetMapping("/restaurants/{id}/menu")
    public ResponseEntity<ResponseRestaurantWithMenuDTO> listRestaurantMenu(@PathVariable(value = "id") Long restaurantId) {
        return ResponseEntity.status(200).body(service.findAllRestaurantMenu(restaurantId));
    }

    @PostMapping(value = "/restaurants/{id}/menu", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addItemMenu(
            @PathVariable(value = "id") Long id,
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") String price,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        Double p = Double.parseDouble(price);
        System.out.println(p);
        service.createMenuItem(id, new RequestMenuItem(name, description, p, ""), imageFile);
        return ResponseEntity.status(201).body("Menu criado!");
    }

    @PutMapping(value = "/menu/{itemId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateItemMenu(
            @PathVariable(value = "itemId") Long itemId,
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("price") String price,
            @RequestPart(value = "image", required = false) MultipartFile imageFile
    ) {
        Double p = Double.parseDouble(price);
        service.updateItemMenu(itemId, new RequestMenuItem(name, description, p, ""), imageFile);
        return ResponseEntity.status(200).body("Menu atualizado!");
    }

    @DeleteMapping("/menu/{itemId}")
    public ResponseEntity<String> deleteItemMenu(@PathVariable Long itemId){
        service.deleteItemMenu(itemId);
        return ResponseEntity.status(200).body("Menu deletado! "+ itemId);
    }

}
