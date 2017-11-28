package com.gederin.service;

import com.gederin.model.Customer;
import com.gederin.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DatabaseLoaderService {

    private final CustomerRepository customerRepository;

    @PostConstruct
    private void initDatabase() {
        customerRepository.deleteAll();

        Customer customer = new Customer("Alex Sanches", "+379865383");
        customerRepository.save(customer);

        customer = new Customer("Adam And Eva", "+7777777777");
        customerRepository.save(customer);
    }
}