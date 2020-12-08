package whz.pti.pizza.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Pizza;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.UserRepository;
import whz.pti.pizza.demo.domain.repositories.PizzaRepository;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.Role;
import whz.pti.pizza.demo.security.domain.User;

import java.util.List;

@SpringBootApplication
@Slf4j
public class DemoApplication {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    PizzaRepository pizzaRepo;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(){
        return (exp)->{
            log.info("init DB");

            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            Customer c1 = new Customer();
            c1.setFirstName("Chuck");
            c1.setLastName("Palahniuk");
            User u1 = new User();
            u1.setLoginName("bnutz");
            c1.setLoginName("bnutz");
            u1.setPasswordHash(passwordEncoder.encode("n1"));
            userRepository.save(u1);
            c1.setUser(u1);
            customerRepo.save(c1);
            Cart cart1 = new Cart();
            cart1.setCustomer(c1);
            cartRepo.save(cart1);
            log.info("customer1 "+c1);

            Customer c2 = new Customer();
            c2.setFirstName("David");
            c2.setLastName("Fincher");
            c2.setLoginName("cnutz");
            User u2 = new User();
            u2.setLoginName("cnutz");
            u2.setPasswordHash(passwordEncoder.encode("n2"));
            userRepository.save(u2);
            c2.setUser(u2);
            customerRepo.save(c2);
            Cart cart2 = new Cart();
            cart2.setCustomer(c2);
            //second have not cart for now
            cartRepo.save(cart2);
            log.info("customer2 "+c2);

            User admin = new User();
            admin.setLoginName("admin");
            admin.setRole(Role.ADMIN);
            admin.setPasswordHash(passwordEncoder.encode("a1"));
            userRepository.save(admin);
            log.info("admin "+admin);

            Pizza pizza1 = new Pizza();
            pizza1.setName("Margarita");
            pizza1.setPriceSmall(2.5);
            pizza1.setPriceMedium(3.5);
            pizza1.setPriceLarge(4.5);

            Pizza pizza2 = new Pizza();
            pizza2.setName("Peperoni");
            pizza2.setPriceSmall(3.5);
            pizza2.setPriceMedium(4.5);
            pizza2.setPriceLarge(5.5);

            Pizza pizza3 = new Pizza();
            pizza3.setName("Mozarella");
            pizza3.setPriceSmall(3);
            pizza3.setPriceMedium(4);
            pizza3.setPriceLarge(5);
            pizzaRepo.saveAll(List.of(pizza1,pizza2,pizza3));
        };
    }
}
