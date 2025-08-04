package com.foodorderx.orderservice.service;

import com.foodorderx.orderservice.dto.RequestCreate;
import com.foodorderx.orderservice.entity.Order;
import com.foodorderx.orderservice.entity.OrderItem;
import com.foodorderx.orderservice.enums.OrderStatusEnum;
import com.foodorderx.orderservice.repository.OrderItemRepository;
import com.foodorderx.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public void createOrder(RequestCreate body) {
        double totalAmount = body.items().stream().mapToDouble(
                item -> item.price() * item.quantity()
        ).sum();

        Order order = new Order(
                body.userId(),
                body.restaurantId(),
                OrderStatusEnum.AGUARDANDO_PAGAMENTO,
                totalAmount,
                new Date()
        );

        Order orderSave = repository.save(order);

        List<OrderItem> orderItems = body.items().stream().map(
                item -> new OrderItem(
                        item.menuItemId(),
                        item.quantity(),
                        item.price(),
                        orderSave
                )
        ).toList();

        orderItemRepository.saveAll(orderItems);

    }

}
