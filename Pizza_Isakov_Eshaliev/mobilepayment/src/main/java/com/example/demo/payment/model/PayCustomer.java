package com.example.demo.payment.model;

import com.example.demo.payment.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class PayCustomer extends BaseEntity<Long> {

    private String loginName;

    @Enumerated(EnumType.STRING)
    private State state;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    public PayCustomer() {
        this.account = new Account();
    }

    public PayCustomer(String loginName) {
        this.account = new Account();
        this.loginName = loginName;
        this.state = State.doesNotExist;
    }
}
