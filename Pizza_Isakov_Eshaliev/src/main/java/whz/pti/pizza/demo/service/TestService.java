package whz.pti.pizza.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whz.pti.pizza.demo.domain.*;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.ItemRepository;
import whz.pti.pizza.demo.domain.repositories.PizzaRepository;

import javax.transaction.Transactional;

@Service
@Slf4j
public class TestService {

    @Autowired
    PizzaRepository pizzaRepo;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    ItemRepository itemRepo;
    @Autowired
    CustomerRepository customerRepo;

    @Transactional
    public void test(){
        Pizza pizza1 = new Pizza();
        pizza1.setPriceLarge(6);
        pizza1.setName("Peperoni");
        pizzaRepo.save(pizza1);
        log.info("Pizza1 id "+pizza1.getId());

        Pizza pizza2 = new Pizza();
        pizza2.setPriceMedium(7.5);
        pizza2.setName("Veggie");
        pizzaRepo.save(pizza2);
        log.info("Pizza2 id "+pizza2.getId());
        log.info("Pizza1 Pizza2 equals "+pizza1.equals(pizza2));

        Item item1 = new Item();
        Item item2 = new Item();
        item1.setPizza(pizza1);
        item1.setPizzaSize(PizzaSize.LARGE);
        item1.setQuantity(2);

        item2.setPizza(pizza2);
        item2.setPizzaSize(PizzaSize.MEDIUM);
        item2.setQuantity(5);


        itemRepo.save(item1);
        itemRepo.save(item2);
        log.info("item1 "+item1.getId());
        log.info("item2 "+item2.getId());
        log.info("Item created ");

        Cart cart = new Cart();
        cart.getItems().put(item1.getId(),item1);
        cart.getItems().put(item2.getId(),item2);
        cartRepo.save(cart);
        log.info("Cart id "+cart.getId());
        log.info("Cart created "+cart);


        Customer customer = new Customer();
        customer.setFirstName("Ataibek");
        customer.setLastName("Isakov");

        cartRepo.delete(cart);
        log.info("deleted cart");

    }
}
