package com.yosiefgobeze.onlinegrocery.service;

import com.yosiefgobeze.onlinegrocery.dto.CustomerCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.CustomerResponse;
import com.yosiefgobeze.onlinegrocery.dto.CustomerUpdateRequest;
import com.yosiefgobeze.onlinegrocery.exception.CustomerHasOrdersException;
import com.yosiefgobeze.onlinegrocery.model.Customer;
import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomers();

    CustomerResponse createCustomer(CustomerCreateRequest request);

    CustomerResponse getCustomerById(Long id);

    CustomerResponse updateCustomerById(CustomerUpdateRequest request, Long id);

    void deleteCustomerById(Long id);
}
