package whz.pti.pizza.demo.security.domain;

import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;



@ToString
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private Customer customer;

    public CurrentUser(Customer customer) {
        super(customer.getLoginName(),customer.getPasswordHash(),AuthorityUtils.createAuthorityList(customer.getRole().toString()));
        this.customer = customer;
    }


    public Customer getCustomer() {
        return customer;
    }
}
