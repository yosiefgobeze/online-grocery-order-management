package com.yosiefgobeze.onlinegrocery.service.impl;

import com.yosiefgobeze.onlinegrocery.dto.OrderCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.OrderResponse;
import com.yosiefgobeze.onlinegrocery.exception.CustomerNotFoundException;
import com.yosiefgobeze.onlinegrocery.exception.GroceryItemNotFoundException;
import com.yosiefgobeze.onlinegrocery.exception.OrderNotFoundException;
import com.yosiefgobeze.onlinegrocery.model.Customer;
import com.yosiefgobeze.onlinegrocery.model.GroceryItem;
import com.yosiefgobeze.onlinegrocery.model.Order;
import com.yosiefgobeze.onlinegrocery.repository.CustomerRepository;
import com.yosiefgobeze.onlinegrocery.repository.GroceryItemRepository;
import com.yosiefgobeze.onlinegrocery.repository.OrderRepository;
import com.yosiefgobeze.onlinegrocery.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository groceryItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, GroceryItemRepository groceryItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public OrderResponse createOrder(OrderCreateRequest request) {
        Long customerId = request.getCustomerId();
        Set<Long> groceryItemIds = request.getGroceryItemIds();
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));

        Set<GroceryItem> groceryItems = new HashSet<>();

            for (Long groceryItemId: groceryItemIds){
                GroceryItem groceryItem = groceryItemRepository.findById(groceryItemId)
                        .orElseThrow(() ->
                                new GroceryItemNotFoundException(groceryItemId));

                groceryItems.add(groceryItem);
            }
        Order savedOrder = orderRepository.save(createNewOrder(customer, groceryItems));
        return mapToResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return mapToResponse(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        orderRepository.delete(order);
    }

    private Order createNewOrder(Customer customer, Set<GroceryItem> groceryItems){
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (GroceryItem groceryItem: groceryItems){
            totalPrice = totalPrice.add(groceryItem.getPrice());
        }
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setGroceryItems(groceryItems);
        newOrder.setTotalPrice(totalPrice);
        newOrder.setOrderDate(LocalDate.now());
        return newOrder;
    }

    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setCustomerId(order.getCustomer().getId());

        Set<Long> groceryItemIds = new HashSet<>();

        for (GroceryItem groceryItem : order.getGroceryItems()) {
            groceryItemIds.add(groceryItem.getId());
        }

        response.setGroceryItemIds(groceryItemIds);
        response.setOrderDate(order.getOrderDate());
        response.setTotalPrice(order.getTotalPrice());

        return response;
    }
}
