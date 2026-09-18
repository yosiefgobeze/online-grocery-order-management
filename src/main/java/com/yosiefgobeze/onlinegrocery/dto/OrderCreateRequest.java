package com.yosiefgobeze.onlinegrocery.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class OrderCreateRequest {
    @NotNull
    private Long customerId;

    @NotEmpty
    private Set<Long> groceryItemIds;
}
