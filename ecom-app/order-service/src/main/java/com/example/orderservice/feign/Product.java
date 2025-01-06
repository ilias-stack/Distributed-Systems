package com.example.orderservice.feign;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
}
