package com.yosiefgobeze.onlinegrocery.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="grocery_items")
@Getter
@Setter
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer quantity;
    @ManyToMany(mappedBy = "groceryItems")
    private Set<Order> orders = new HashSet<>();
}
