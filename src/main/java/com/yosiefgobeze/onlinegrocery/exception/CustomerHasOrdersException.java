package com.yosiefgobeze.onlinegrocery.exception;

public class CustomerHasOrdersException extends RuntimeException {
    public CustomerHasOrdersException(Long id) {
        super("Customer with id " + id +
                " cannot be deleted because they have existing orders.");

    }
}
