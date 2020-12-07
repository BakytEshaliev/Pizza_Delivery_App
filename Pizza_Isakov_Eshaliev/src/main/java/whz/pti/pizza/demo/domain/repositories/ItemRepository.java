package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import whz.pti.pizza.demo.domain.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {
}
