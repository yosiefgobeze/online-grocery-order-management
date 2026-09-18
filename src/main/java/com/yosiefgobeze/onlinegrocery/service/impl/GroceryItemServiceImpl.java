package com.yosiefgobeze.onlinegrocery.service.impl;

import com.yosiefgobeze.onlinegrocery.dto.GroceryItemUpdateRequest;
import com.yosiefgobeze.onlinegrocery.exception.GroceryItemIsOrdered;
import com.yosiefgobeze.onlinegrocery.exception.GroceryItemNotFoundException;
import com.yosiefgobeze.onlinegrocery.model.GroceryItem;
import com.yosiefgobeze.onlinegrocery.repository.GroceryItemRepository;
import com.yosiefgobeze.onlinegrocery.service.GroceryItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemServiceImpl implements GroceryItemService {
    private final GroceryItemRepository groceryItemRepository;

    public GroceryItemServiceImpl(GroceryItemRepository groceryItemRepository) {
        this.groceryItemRepository = groceryItemRepository;
    }

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepository.findAll();
    }

    @Override
    public GroceryItem createGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    public GroceryItem getGroceryItemById(Long id) {
        return groceryItemRepository.findById(id).orElseThrow(() -> new GroceryItemNotFoundException(id));
    }

    @Override
    public GroceryItem updateGroceryItemById(GroceryItemUpdateRequest request, Long id) {
        GroceryItem existingGroceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new GroceryItemNotFoundException(id));
        existingGroceryItem.setName(request.getName());
        existingGroceryItem.setCategory(request.getCategory());
        existingGroceryItem.setPrice(request.getPrice());
        existingGroceryItem.setQuantity(request.getQuantity());
        return groceryItemRepository.save(existingGroceryItem);

    }

    @Override
    public void deleteGroceryItem(Long id) {
        GroceryItem groceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new GroceryItemNotFoundException(id));
        if(!groceryItem.getOrders().isEmpty()){
            throw new GroceryItemIsOrdered(id);
        }
        groceryItemRepository.delete(groceryItem);
    }
}
