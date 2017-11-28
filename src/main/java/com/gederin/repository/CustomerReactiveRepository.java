package com.gederin.repository;


import com.gederin.model.Customer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Mono;

public interface CustomerReactiveRepository extends ReactiveMongoRepository<Customer, String>{
    Mono<Customer> findCustomerByName(String name);
}
