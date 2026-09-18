package com.yosiefgobeze.onlinegrocery.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GroceryItemResponse {
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer quantity;
}
