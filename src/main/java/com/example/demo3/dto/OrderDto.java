package com.example.demo3.dto;

import lombok.Data;

@Data
public class OrderDto {
    private String productId;
    private int qty;
    private int unitPrice;
    private int totalPrice;
    private String orderId;
    private String userId;
}
