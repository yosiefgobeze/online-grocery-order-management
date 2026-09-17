package com.yosiefgobeze.onlinegrocery.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    private LocalDate orderDate;
    private BigDecimal totalPrice;
    @ManyToMany
    @JoinTable(
            name = "order_grocery_items",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "grocery_item_id")
    )
    private Set<GroceryItem> groceryItems = new HashSet<>();
}
