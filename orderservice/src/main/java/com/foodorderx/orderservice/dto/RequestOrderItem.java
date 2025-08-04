package com.foodorderx.orderservice.dto;

public record RequestOrderItem(Long menuItemId, Integer quantity, Double price) {
}
