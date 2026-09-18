package com.yosiefgobeze.onlinegrocery.service;

import com.yosiefgobeze.onlinegrocery.dto.OrderCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.OrderResponse;
import com.yosiefgobeze.onlinegrocery.model.Order;

public interface OrderService {

    OrderResponse createOrder(OrderCreateRequest request);

}
