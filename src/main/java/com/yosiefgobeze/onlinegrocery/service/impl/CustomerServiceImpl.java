package com.yosiefgobeze.onlinegrocery.service.impl;

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
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer updateCustomerById(CustomerUpdateRequest request, Long id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        existingCustomer.setName(request.getName());
        existingCustomer.setAddress(request.getAddress());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setPhone(request.getPhone());

        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
        if (!customer.getOrders().isEmpty()){
            throw new CustomerHasOrdersException(id);
        }
        customerRepository.delete(customer);
    }
}
