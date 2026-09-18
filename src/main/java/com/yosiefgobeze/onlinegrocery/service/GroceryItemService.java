package com.yosiefgobeze.onlinegrocery.service;

import com.yosiefgobeze.onlinegrocery.dto.GroceryItemCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.GroceryItemResponse;
import com.yosiefgobeze.onlinegrocery.dto.GroceryItemUpdateRequest;
import com.yosiefgobeze.onlinegrocery.model.GroceryItem;
import jakarta.validation.Valid;

import java.util.List;

public interface GroceryItemService {
    List<GroceryItemResponse> getAllGroceryItems();

    GroceryItemResponse createGroceryItem(GroceryItemCreateRequest groceryItem);

    GroceryItemResponse getGroceryItemById(Long id);

    GroceryItemResponse updateGroceryItemById(GroceryItemUpdateRequest request, Long id);

    void deleteGroceryItem(Long id);
}
