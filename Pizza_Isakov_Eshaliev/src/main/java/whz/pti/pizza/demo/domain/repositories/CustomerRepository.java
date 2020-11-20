package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import whz.pti.pizza.demo.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
