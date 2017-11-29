package com.gederin.controller;

import com.gederin.model.Customer;
import com.gederin.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerController {
    private static final String REDIRECT = "redirect:/";

    private final CustomerService customerService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("activeProfile", activeProfile);
        model.addAttribute("customers", customerService.fetchAllCustomers());
        model.addAttribute("customer", new Customer());

        return "index";
    }

    @PostMapping("/add")
    public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError -> log.error(objectError.toString()));

            return REDIRECT;
        }

        customerService.saveCustomer(customer).block();

        return REDIRECT;
    }
}