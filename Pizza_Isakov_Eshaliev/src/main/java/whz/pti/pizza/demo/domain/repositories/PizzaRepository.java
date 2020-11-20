package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import whz.pti.pizza.demo.domain.Pizza;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza,Integer> {

}
