package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import whz.pti.pizza.demo.domain.DeliveryAddress;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
    DeliveryAddress getById(Long id);
}
