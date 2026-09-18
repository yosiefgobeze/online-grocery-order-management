package com.yosiefgobeze.onlinegrocery.repository;

import com.yosiefgobeze.onlinegrocery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
