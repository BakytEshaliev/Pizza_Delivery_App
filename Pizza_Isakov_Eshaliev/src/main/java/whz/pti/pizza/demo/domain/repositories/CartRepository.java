package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.security.domain.Customer;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart,Long> {
    Cart getByCustomer(Customer customer);
}
