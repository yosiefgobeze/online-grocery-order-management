package com.yosiefgobeze.onlinegrocery.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private Set<Long> groceryItemIds;
    private LocalDate orderDate;
    private BigDecimal totalPrice;
}
