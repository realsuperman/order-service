package com.example.demo3.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    private String productId;
    private int qty;
    private int unitPrice;
    private int totalPrice;
    private Date createdAt;
    private String orderId;
}
