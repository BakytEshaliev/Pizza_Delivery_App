package whz.pti.pizza.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.Role;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class InitDB {

    @Autowired
    CustomerRepository customerRepository;
    @PostConstruct
    public void init(){

    }
}
