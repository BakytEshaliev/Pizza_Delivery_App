package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import whz.pti.pizza.demo.domain.DeliveryAddress;
import whz.pti.pizza.demo.security.domain.Customer;

import java.util.List;
@Repository

public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> getByCustomers(Customer customer);
    DeliveryAddress getById(Long id);
}
