package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import whz.pti.pizza.demo.security.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginName(String loginName);
}
