package whz.pti.pizza.demo.security.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.AuthorityUtils;



@ToString
@Getter
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public CurrentUser(User user) {
        super(user.getLoginName(),user.getPasswordHash(),AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }


    public User getUser() {
        return user;
    }

}
