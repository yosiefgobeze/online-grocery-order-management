package com.yosiefgobeze.onlinegrocery.controller;

import com.yosiefgobeze.onlinegrocery.dto.OrderCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.OrderResponse;
import com.yosiefgobeze.onlinegrocery.service.OrderService;
import jakarta.validation.Valid;
import org.hibernate.query.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.ResponseAPDU;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderCreateRequest request){
        OrderResponse order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
