package com.gederin.service;

import com.gederin.model.Customer;
import com.gederin.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Flux<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
    }
}