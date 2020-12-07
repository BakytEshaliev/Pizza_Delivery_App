package whz.pti.pizza.demo.domain.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import whz.pti.pizza.demo.security.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginName(String loginName);
}
