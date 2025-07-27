package com.foodorderx.restaurant.dto;

import com.foodorderx.restaurant.entity.Restaurant;

public class ResponseRestaurant {
    private final Long id;
    private final String name;
    private final String description;
    private final String address;
    private final String imageUrl;
    private final boolean open;

    public ResponseRestaurant(Long id, String name, String description, String address, String imageUrl, boolean open) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.open = open;
    }

    public ResponseRestaurant(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.address = restaurant.getAddress();
        this.imageUrl = restaurant.getImageUrl();
        this.open = restaurant.isOpen();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isOpen() {
        return open;
    }
}
