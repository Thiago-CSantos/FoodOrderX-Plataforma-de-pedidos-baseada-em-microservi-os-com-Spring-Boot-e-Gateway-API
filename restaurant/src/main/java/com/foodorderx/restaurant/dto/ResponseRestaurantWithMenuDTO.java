package com.foodorderx.restaurant.dto;

import java.util.List;

public record ResponseRestaurantWithMenuDTO(
        Long id,
        String name,
        String description,
        String address,
        String imageUrl,
        boolean open,
        List<ResponseMenuItem> menuItems
) {
}
