package com.yosiefgobeze.onlinegrocery.service.impl;

import com.yosiefgobeze.onlinegrocery.dto.GroceryItemCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.GroceryItemResponse;
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
    public List<GroceryItemResponse> getAllGroceryItems() {
        return groceryItemRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public GroceryItemResponse createGroceryItem(GroceryItemCreateRequest request) {
        GroceryItem groceryItem = new GroceryItem();

        groceryItem.setName(request.getName());
        groceryItem.setQuantity(request.getQuantity());
        groceryItem.setPrice(request.getPrice());
        groceryItem.setCategory(request.getCategory());

        GroceryItem savedGroceryItem = groceryItemRepository.save(groceryItem);
        return mapToResponse(savedGroceryItem);
    }

    @Override
    public GroceryItemResponse getGroceryItemById(Long id) {
        GroceryItem existingGroceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new GroceryItemNotFoundException(id));
        return mapToResponse(existingGroceryItem);
    }

    @Override
    public GroceryItemResponse updateGroceryItemById(GroceryItemUpdateRequest request, Long id) {
        GroceryItem existingGroceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new GroceryItemNotFoundException(id));
        existingGroceryItem.setName(request.getName());
        existingGroceryItem.setCategory(request.getCategory());
        existingGroceryItem.setPrice(request.getPrice());
        existingGroceryItem.setQuantity(request.getQuantity());
        GroceryItem savedGroceryItem = groceryItemRepository.save(existingGroceryItem);

        return mapToResponse(savedGroceryItem);
    }

    @Override
    public void deleteGroceryItem(Long id) {
        GroceryItem groceryItem = groceryItemRepository.findById(id).orElseThrow(() -> new GroceryItemNotFoundException(id));
        if(!groceryItem.getOrders().isEmpty()){
            throw new GroceryItemIsOrdered(id);
        }
        groceryItemRepository.delete(groceryItem);
    }

    private GroceryItemResponse mapToResponse(GroceryItem groceryItem) {

        GroceryItemResponse response = new GroceryItemResponse();

        response.setId(groceryItem.getId());
        response.setName(groceryItem.getName());
        response.setCategory(groceryItem.getCategory());
        response.setPrice(groceryItem.getPrice());
        response.setQuantity(groceryItem.getQuantity());

        return response;
    }
}
