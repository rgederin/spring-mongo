package com.gederin.repository;


import com.gederin.model.Customer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByFirstName(String firstName);
}