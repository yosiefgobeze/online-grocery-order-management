package com.yosiefgobeze.onlinegrocery.exception;

public class OrderCanNotBePlaced extends RuntimeException{
    public OrderCanNotBePlaced(Long id){
        super("Order can not be placed for customer with id: " + id + " We are out off Grocery.");
    }
}
