package com.sohan.customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping()
    public ResponseEntity<Integer> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        return new ResponseEntity<Integer>(
            service.createCustomer(customerRequest),HttpStatus.OK
        );
    }

    @PutMapping()
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ){
        service.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
       return new ResponseEntity<>(
               service.findAllCustomers(),HttpStatus.OK
       );
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> customerExistsById(
            @PathVariable("customer-id") Integer customerId
    ){
        return new ResponseEntity<>(
                service.existById(customerId),HttpStatus.OK
        );
    }

    @GetMapping("/find/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomerById(
            @PathVariable("customer-id") Integer customerId
    ){
        return new ResponseEntity<>(
                service.findById(customerId),HttpStatus.OK
        );
    }
    @DeleteMapping("/delete/{customer-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("customer-id") Integer customerId
    ){
        service.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
