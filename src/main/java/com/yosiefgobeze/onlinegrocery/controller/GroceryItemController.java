package com.yosiefgobeze.onlinegrocery.controller;

import com.yosiefgobeze.onlinegrocery.dto.CustomerUpdateRequest;
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
    public ResponseEntity<List<GroceryItem>> getAllGroceryItems(){
        List<GroceryItem> groceryItems = groceryItemService.getAllGroceryItems();
        return ResponseEntity.ok(groceryItems);
    }

    @PostMapping
    public ResponseEntity<GroceryItem> createGroceryItem(@RequestBody GroceryItem groceryItem){
        GroceryItem savedGroceryItem = groceryItemService.createGroceryItem(groceryItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroceryItem);
    }

    @GetMapping( "/{id}")
    public ResponseEntity<GroceryItem> getGroceryItemById(@PathVariable Long id){
        GroceryItem groceryItem = groceryItemService.getGroceryItemById(id);
        return ResponseEntity.ok(groceryItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroceryItem> updateGroceryItemById(
            @Valid @RequestBody GroceryItemUpdateRequest request,
            @PathVariable Long id){
        GroceryItem updatedGroceryItem = groceryItemService.updateGroceryItemById(request, id);
        return ResponseEntity.ok(updatedGroceryItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroceryItem(@PathVariable Long id){
        groceryItemService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }
}
