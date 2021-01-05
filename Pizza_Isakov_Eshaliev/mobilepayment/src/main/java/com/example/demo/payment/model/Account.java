package com.example.demo.payment.model;

import com.example.demo.payment.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Getter
@Setter
@Entity
public class Account extends BaseEntity<Long> {

    private double balance;
    @JsonBackReference
    @OneToOne(mappedBy = "account")
    private PayCustomer payCustomer;

    public Account() {
        this.balance = 100;
    }

    public void depositBalance(double amount){
        this.balance += amount;
    }

    public void payForOrder(double amount){
        this.balance -= amount;
    }
}
