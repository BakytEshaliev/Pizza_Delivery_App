package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.security.domain.Customer;

public interface CartRepository extends CrudRepository<Cart,Long> {
    Cart getByCustomer(Customer customer);
}
