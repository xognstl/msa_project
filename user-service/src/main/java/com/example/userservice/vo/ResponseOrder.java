package com.example.userservice.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPrice;  // 단가
    private Integer totalPrice;
    private Date createAt;

    private String orderId;
}
