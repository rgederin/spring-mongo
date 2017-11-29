package com.gederin.controller;

import com.gederin.service.CustomerService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import reactor.core.publisher.Flux;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private Model model;

    private CustomerController customerController;

    @Before
    public void setUp() {
        customerController = new CustomerController(customerService);
    }

    @Test
    public void shouldInvokeServiceFetchAllCustomers() {
        when(customerService.fetchAllCustomers()).thenReturn(Flux.just());

        customerController.index(model);

        verify(customerService, times(1)).fetchAllCustomers();
    }
}