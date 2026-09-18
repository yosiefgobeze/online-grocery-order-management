package com.yosiefgobeze.onlinegrocery.repository;

import com.yosiefgobeze.onlinegrocery.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
}
