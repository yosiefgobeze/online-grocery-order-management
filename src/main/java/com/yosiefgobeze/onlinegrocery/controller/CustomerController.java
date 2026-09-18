package com.yosiefgobeze.onlinegrocery.controller;

import com.yosiefgobeze.onlinegrocery.dto.CustomerCreateRequest;
import com.yosiefgobeze.onlinegrocery.dto.CustomerResponse;
import com.yosiefgobeze.onlinegrocery.dto.CustomerUpdateRequest;
import com.yosiefgobeze.onlinegrocery.model.Customer;
import com.yosiefgobeze.onlinegrocery.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
        List<CustomerResponse> listOfCustomers = customerService.getAllCustomers();
        return ResponseEntity.ok(listOfCustomers);
    }

    @PostMapping()
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerCreateRequest request){
        CustomerResponse savedCustomer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @GetMapping( "/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id){
        CustomerResponse response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomerById(
            @Valid @RequestBody CustomerUpdateRequest request,
            @PathVariable Long id){
        CustomerResponse updatedCustomer = customerService.updateCustomerById(request, id);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }
}
