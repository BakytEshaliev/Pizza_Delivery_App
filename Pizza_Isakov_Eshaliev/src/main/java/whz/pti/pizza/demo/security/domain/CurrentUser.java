package whz.pti.pizza.demo.security.domain;

import lombok.Setter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@ToString
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    @Setter
    private User user;

    public CurrentUser(User user) {
        super(user.getLoginName(),user.getPasswordHash(),AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}
