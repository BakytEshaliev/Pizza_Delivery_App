package com.example.demo.payment.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayCustomerRepository extends CrudRepository<PayCustomer,Long> {
    Optional<PayCustomer> getByLoginName(String loginName);
}
