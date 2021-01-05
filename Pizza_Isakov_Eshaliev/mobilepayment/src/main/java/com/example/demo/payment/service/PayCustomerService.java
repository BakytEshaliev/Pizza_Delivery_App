package com.example.demo.payment.service;

import com.example.demo.payment.model.PayCustomer;
import com.example.demo.payment.model.State;

public interface PayCustomerService {
    void openAccount(String loginName);
    boolean transfer(String loginName,double amount);
    double getAccBalanceByName(String loginName);
    State getState(String loginName);
    PayCustomer getPayCustomer(String loginName);
    boolean containsAndAvailable(String loginName);
    boolean deleteUser(String customerId);
    void changeToSuspend(String customerId);

}
