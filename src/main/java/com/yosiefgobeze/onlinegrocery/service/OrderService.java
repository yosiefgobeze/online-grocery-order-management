package com.yosiefgobeze.onlinegrocery.service;

import com.yosiefgobeze.onlinegrocery.dto.OrderCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.OrderResponse;
import com.yosiefgobeze.onlinegrocery.model.Order;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderCreateRequest request);
    List<OrderResponse> getAllOrders();
    OrderResponse getOrderById(Long id);
    void deleteOrderById(Long id);

}
