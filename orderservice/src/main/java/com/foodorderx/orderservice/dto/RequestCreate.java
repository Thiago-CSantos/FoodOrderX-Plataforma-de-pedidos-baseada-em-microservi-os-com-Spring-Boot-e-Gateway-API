package com.foodorderx.orderservice.dto;

import java.util.List;
import java.util.UUID;

public record RequestCreate(UUID userId, Long restaurantId, List<RequestOrderItem> items, String paymentMethod) {
}
