package whz.pti.pizza.demo.security.sevice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.CurrentUser;

@Service
@Slf4j
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepository customerRepo;

    @Override
    public CurrentUser loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Customer customer = customerRepo.findByLoginName(loginName);
//        log.info("Customer "+customer);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer with loginName = " + loginName + " cannot be not found");
        }
        return new CurrentUser(customer);
    }
}
