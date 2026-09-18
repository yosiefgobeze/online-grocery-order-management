package com.yosiefgobeze.onlinegrocery.service;

import com.yosiefgobeze.onlinegrocery.dto.CustomerUpdateRequest;
import com.yosiefgobeze.onlinegrocery.exception.CustomerHasOrdersException;
import com.yosiefgobeze.onlinegrocery.model.Customer;
import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long id);

    Customer updateCustomerById(CustomerUpdateRequest request, Long id);

    void deleteCustomerById(Long id);
}
