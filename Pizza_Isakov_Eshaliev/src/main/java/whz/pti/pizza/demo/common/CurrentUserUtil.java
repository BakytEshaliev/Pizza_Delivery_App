package whz.pti.pizza.demo.common;


import org.springframework.ui.Model;
import whz.pti.pizza.demo.security.domain.CurrentUser;

public class CurrentUserUtil {
	public static CurrentUser getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        return currentUser;
    }
}
