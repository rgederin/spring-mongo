package com.gederin.repository;


import com.gederin.model.Customer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerReactiveRepositoryIntegrationTest {

    @Autowired
    private CustomerReactiveRepository customerReactiveRepository;

    private Customer customer;

    @Before
    public void setUp(){
        customer = new Customer("Ruslan Gederin", "21931298312");

        customerReactiveRepository.deleteAll().block();
    }

    @Test
    public void shouldSaveCustomer(){
        assertThat(customerReactiveRepository.count().block(), equalTo(0L));

        customerReactiveRepository.save(customer).block();

        assertThat(customerReactiveRepository.count().block(), equalTo(1L));
    }

    @Test
    public void shouldFindCustomerByName(){
        customerReactiveRepository.save(customer).block();

        Customer customerFromMongo = customerReactiveRepository.findCustomerByName("Ruslan Gederin").block();

        assertThat(customerFromMongo.equals(customer), equalTo(true));
    }
}