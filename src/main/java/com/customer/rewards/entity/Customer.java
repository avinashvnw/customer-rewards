package com.customer.rewards.entity;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "CUSTOMER_FIRSTNAME")
    private String customerFirstName;
    @Column(name = "CUSTOMER_LASTNAME")
    private String customerLastName;
}
