package com.gederin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@ToString
@NoArgsConstructor
public class Customer {

    @Id
    private String id;

    private @NotBlank String name;

    private @NotBlank @Digits(integer = 10, fraction = 0, message = "Must be numeric value") String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
