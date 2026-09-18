package com.yosiefgobeze.onlinegrocery.exception;

public class GroceryItemNotFoundException extends RuntimeException {

    public GroceryItemNotFoundException(Long id){
        super("Could not find grocery item with id: " + id);
    }
}
