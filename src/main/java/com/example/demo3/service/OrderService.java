package com.example.demo3.service;

import com.example.demo3.dto.OrderDto;
import com.example.demo3.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrderByUserId(String userId);
}
