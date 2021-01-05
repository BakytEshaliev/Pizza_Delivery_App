package com.example.demo.payment.service;

import com.example.demo.payment.model.PayCustomer;
import com.example.demo.payment.model.PayCustomerRepository;
import com.example.demo.payment.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PayCustomerServiceImpl implements PayCustomerService{

    @Autowired
    PayCustomerRepository payCustomerRepo;
    @Override
    public void openAccount(String loginName){
        PayCustomer payCustomer = payCustomerRepo.getByLoginName(loginName).orElse(new PayCustomer(loginName));
        payCustomer.setState(State.available);
        payCustomerRepo.save(payCustomer);
    }
    @Override
    public boolean containsAndAvailable(String loginName){
        Optional<PayCustomer> payCustomer = payCustomerRepo.getByLoginName(loginName);
        if (payCustomer.isPresent()) {
            PayCustomer customer = payCustomer.get();
            return customer.getState() == State.available;
        }
        return false;
    }
    @Override
    public boolean transfer(String loginName, double amount){
        if (containsAndAvailable(loginName)){
            PayCustomer payCustomer = getPayCustomer(loginName);
            payCustomer.getAccount().payForOrder(amount);
            payCustomerRepo.save(payCustomer);
            return true;
        }
        return false;
    }

    public double getAccBalanceByName(String loginName){
        Optional<PayCustomer> payCustomer = payCustomerRepo.getByLoginName(loginName);
        return payCustomer.map(customer -> customer.getAccount().getBalance()).orElse(-1.0);
    }

    @Override
    public State getState(String loginName){
        Optional<PayCustomer> payCustomer = payCustomerRepo.getByLoginName(loginName);
        return (payCustomer.isPresent()) ?
                payCustomer.get().getState():
                State.doesNotExist;
    }
    @Override
    public PayCustomer getPayCustomer(String loginName){
        Optional<PayCustomer> optionalPayCustomer = payCustomerRepo.getByLoginName(loginName);
        return optionalPayCustomer.orElse(null);
    }
    @Override
    public void changeToSuspend(String customerId){
        PayCustomer payCustomer = getPayCustomer(customerId);
        if (payCustomer != null) {
            payCustomer.setState(State.suspend);
            payCustomerRepo.save(payCustomer);
        }
    }
    @Override
    public boolean deleteUser(String customerId){
        PayCustomer payCustomer = getPayCustomer(customerId);
        if (payCustomer != null) {
            payCustomer.setState(State.doesNotExist);
            payCustomer.getAccount().setBalance(0);
            payCustomerRepo.save(payCustomer);
            return true;
        }
        return false;
    }
}
