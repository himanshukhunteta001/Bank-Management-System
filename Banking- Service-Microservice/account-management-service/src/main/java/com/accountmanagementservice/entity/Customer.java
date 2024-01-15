package com.accountmanagementservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private int customerId;
    private String firstName;
    private String lastName;
    private String accountNumber;
    private String phoneNumber;
    private String address;
}

