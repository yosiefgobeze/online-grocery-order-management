package com.yosiefgobeze.onlinegrocery.controller;

import com.yosiefgobeze.onlinegrocery.dto.CustomerUpdateRequest;
import com.yosiefgobeze.onlinegrocery.dto.GroceryItemCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.GroceryItemResponse;
import com.yosiefgobeze.onlinegrocery.dto.GroceryItemUpdateRequest;
import com.yosiefgobeze.onlinegrocery.model.Customer;
import com.yosiefgobeze.onlinegrocery.model.GroceryItem;
import com.yosiefgobeze.onlinegrocery.service.GroceryItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grocery-items")
public class GroceryItemController {
    private final GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping
    public ResponseEntity<List<GroceryItemResponse>> getAllGroceryItems(){
        List<GroceryItemResponse> groceryItems = groceryItemService.getAllGroceryItems();
        return ResponseEntity.ok(groceryItems);
    }

    @PostMapping
    public ResponseEntity<GroceryItemResponse> createGroceryItem(@RequestBody GroceryItemCreateRequest request){
        GroceryItemResponse savedGroceryItem = groceryItemService.createGroceryItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroceryItem);
    }

    @GetMapping( "/{id}")
    public ResponseEntity<GroceryItemResponse> getGroceryItemById(@PathVariable Long id){
        GroceryItemResponse groceryItem = groceryItemService.getGroceryItemById(id);
        return ResponseEntity.ok(groceryItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItemResponse> updateGroceryItemById(
            @Valid @RequestBody GroceryItemUpdateRequest request,
            @PathVariable Long id){
        GroceryItemResponse updatedGroceryItem = groceryItemService.updateGroceryItemById(request, id);
        return ResponseEntity.ok(updatedGroceryItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id){
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }

}
