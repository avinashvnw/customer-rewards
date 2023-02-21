package com.customer.rewards.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="Transaction")
@Data
public class Transactions {
    @Id
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "transaction_amount")
    int transactionAmount;

    @Column(name = "creation_date")
    Date creationDate;
}
