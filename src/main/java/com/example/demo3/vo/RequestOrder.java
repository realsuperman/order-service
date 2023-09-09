package com.example.demo3.vo;

import lombok.Data;

@Data
public class RequestOrder {
    private String productId;
    private int qty;
    private int unitPrice;
}