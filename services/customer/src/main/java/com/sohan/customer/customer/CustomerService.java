package com.sohan.customer.customer;

import com.sohan.customer.Exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Integer createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        "No customer found with the provided id "+customerRequest.id()
                ));

        mergeCustomer(customer,customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest customerRequest) {
        if(StringUtils.isNotBlank(customerRequest.firstName())){
            customer.setFirstName(customerRequest.firstName());
        }
        if(StringUtils.isNotBlank(customerRequest.lastName())){
            customer.setLastName(customerRequest.lastName());
        }
        if(StringUtils.isNotBlank(customerRequest.email())){
            customer.setEmail(customerRequest.email());
        }
        if(!Objects.isNull(customerRequest.address())){
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomerToResponse)
                .toList();
    }

    public Boolean existById(Integer customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(Integer customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomerToResponse)
                .orElseThrow(()-> new CustomerNotFoundException(
                        "Customer not found with the provided Id"
                ));
    }

    public void deleteCustomer(Integer customerId) {
        customerRepository.deleteById(
                customerId
        );
    }
}
