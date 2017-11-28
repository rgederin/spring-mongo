package com.gederin.service;

import com.gederin.model.Customer;
import com.gederin.repository.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setUp(){
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void shouldInvokeRepositorySaveMethod(){
        customerService.saveCustomer(new Customer());

        verify(customerRepository, times(1)).save(any());
    }

    @Test
    public void shouldInvokeRepositorySaveMethodWithSameCustomer (){
        ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);
        Customer customer = new Customer("Ruslan Gederin", "21931298312");

        customerService.saveCustomer(customer);

        verify(customerRepository, times(1)).save(argumentCaptor.capture());

        assertThat("Repository should save the same object as received", customer.equals(argumentCaptor.getValue()), equalTo(true));
    }
}