package com.gederin.service;

import com.google.common.collect.ImmutableList;

import com.gederin.model.Customer;
import com.gederin.repository.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private Customer customer;

    @Before
    public void setUp() {
        customerService = new CustomerService(customerRepository);
        customer = new Customer("Ruslan Gederin", "21931298312");
    }

    @Test
    public void shouldInvokeRepositorySaveMethod() {
        customerService.saveCustomer(customer);

        verify(customerRepository, times(1)).save(any());
    }

    @Test
    public void shouldInvokeRepositorySaveMethodWithSameCustomer() {
        ArgumentCaptor<Customer> argumentCaptor = ArgumentCaptor.forClass(Customer.class);

        customerService.saveCustomer(customer);

        verify(customerRepository, times(1)).save(argumentCaptor.capture());

        assertThat(customer.equals(argumentCaptor.getValue()), equalTo(true));
    }

    @Test
    public void shouldInvokedRepositoryFindAllWhenFetchAllCustomers() {
        customerService.fetchAllCustomers();

        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnListOfCustomerWhenFetchAllCustomers() {
        List<Customer> customers = ImmutableList.of(customer, new Customer("RG", "33"));
        when(customerRepository.findAll()).thenReturn(customers);

        assertThat(customerService.fetchAllCustomers(), notNullValue());
        assertThat(customerService.fetchAllCustomers().size(), equalTo(2));
        assertThat(customerService.fetchAllCustomers().get(0), equalTo(customer));
    }
}