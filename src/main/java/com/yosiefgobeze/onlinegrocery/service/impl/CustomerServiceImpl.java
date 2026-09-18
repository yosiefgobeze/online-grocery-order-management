package com.yosiefgobeze.onlinegrocery.service.impl;

import com.yosiefgobeze.onlinegrocery.dto.CustomerCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.CustomerResponse;
import com.yosiefgobeze.onlinegrocery.dto.CustomerUpdateRequest;
import com.yosiefgobeze.onlinegrocery.exception.CustomerHasOrdersException;
import com.yosiefgobeze.onlinegrocery.exception.CustomerNotFoundException;
import com.yosiefgobeze.onlinegrocery.model.Customer;
import com.yosiefgobeze.onlinegrocery.repository.CustomerRepository;
import com.yosiefgobeze.onlinegrocery.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    @Override
    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        Customer savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return mapToResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomerById(CustomerUpdateRequest request, Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        existingCustomer.setName(request.getName());
        existingCustomer.setAddress(request.getAddress());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setPhone(request.getPhone());

        Customer savedCustomer = customerRepository.save(existingCustomer);
        return mapToResponse(savedCustomer);
    }

    @Override
    public void deleteCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        if (!customer.getOrders().isEmpty()){
            throw new CustomerHasOrdersException(id);
        }
        customerRepository.delete(customer);
    }

    private CustomerResponse mapToResponse(Customer customer) {
        CustomerResponse response = new CustomerResponse();

        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        response.setAddress(customer.getAddress());
        response.setPhone(customer.getPhone());

        return response;
    }
}
