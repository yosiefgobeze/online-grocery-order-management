package com.yosiefgobeze.onlinegrocery.service;

import com.yosiefgobeze.onlinegrocery.dto.GroceryItemUpdateRequest;
import com.yosiefgobeze.onlinegrocery.model.GroceryItem;
import jakarta.validation.Valid;

import java.util.List;

public interface GroceryItemService {
    List<GroceryItem> getAllGroceryItems();

    GroceryItem createGroceryItem(GroceryItem groceryItem);

    GroceryItem getGroceryItemById(Long id);

    GroceryItem updateGroceryItemById(GroceryItemUpdateRequest request, Long id);

    void deleteGroceryItem(Long id);
}
