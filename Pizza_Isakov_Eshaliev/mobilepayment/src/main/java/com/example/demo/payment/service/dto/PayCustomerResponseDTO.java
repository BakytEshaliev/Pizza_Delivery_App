package com.example.demo.payment.service.dto;

import com.example.demo.payment.model.Account;
import com.example.demo.payment.model.PayCustomer;
import com.example.demo.payment.model.State;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@JsonPropertyOrder({ "id", "name", "state", "account" })
public class PayCustomerResponseDTO { // extends ResourceSupport {

    @JsonProperty("id")
    private long myId;

    private String name;

    @Enumerated(EnumType.STRING)
    private State state;

    private Account account;

    public PayCustomerResponseDTO() {
        account = new Account();
    }

    public PayCustomerResponseDTO(PayCustomer payUser) {
        this.myId = payUser.getId();
        this.account = payUser.getAccount();
        this.name = payUser.getLoginName();
        this.state = payUser.getState();
    }

    public long getMyId() {
        return myId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public String toString() {
        return " [ " +
                myId + " , " +
                name + " , " +
                account.toString() + " , " +
                state + " ]"
//                + getLinks()
                ;
    }

}


