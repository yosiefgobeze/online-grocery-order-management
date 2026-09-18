package com.yosiefgobeze.onlinegrocery.exception;

public class GroceryItemIsOrdered extends RuntimeException{
    public GroceryItemIsOrdered(Long id){
        super("Grocery item with id " + id +
                " cannot be deleted because it is associated with an existing order.");
    }
}
