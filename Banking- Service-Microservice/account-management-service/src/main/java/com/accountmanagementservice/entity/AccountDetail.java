package com.accountmanagementservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Transient;

import lombok.*;


@Entity
@Table(name = "account_dtl")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "balance")
    private int balance;

    @Column(name = "cust_id")
    private int customerId;

    @Transient
    Customer customer;
}
