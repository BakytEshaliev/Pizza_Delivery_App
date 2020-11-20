package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import whz.pti.pizza.demo.domain.Cart;

public interface CartRepository extends CrudRepository<Cart,Long> {
}
