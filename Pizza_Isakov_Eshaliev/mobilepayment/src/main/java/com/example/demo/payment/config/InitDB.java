package com.example.demo.payment.config;

import com.example.demo.payment.model.PayCustomer;
import com.example.demo.payment.model.PayCustomerRepository;
import com.example.demo.payment.model.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class InitDB {

    @Autowired
    PayCustomerRepository payCustomerRepo;

    @PostConstruct
    public void init(){
        log.debug("db init");

        PayCustomer payCustomer = new PayCustomer();
        payCustomer.setLoginName("bnutz");
        payCustomer.setState(State.available);
        payCustomerRepo.save(payCustomer);

        payCustomer = new PayCustomer();
        payCustomer.setLoginName("cnutz");
        payCustomer.setState(State.available);
        payCustomerRepo.save(payCustomer);
    }
}
