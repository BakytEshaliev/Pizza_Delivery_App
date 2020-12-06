package whz.pti.pizza.demo.security.sevice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.pizza.demo.domain.repositories.UserRepository;
import whz.pti.pizza.demo.security.domain.CurrentUser;
import whz.pti.pizza.demo.security.domain.User;

@Service
@Slf4j
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    @Override
    public CurrentUser loadUserByUsername(String loginName) throws UsernameNotFoundException {
        log.info(loginName);
        User user = userRepo.findByLoginName(loginName);
        log.info("User "+user);
        if (user == null) {
            throw new UsernameNotFoundException("Customer with loginName = " + loginName + " cannot be not found");
        }
        return new CurrentUser(user);
    }
}
