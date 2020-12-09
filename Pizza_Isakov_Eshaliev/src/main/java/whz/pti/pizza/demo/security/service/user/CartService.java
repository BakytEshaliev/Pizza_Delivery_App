package whz.pti.pizza.demo.security.service.user;

import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;

public interface CartService {

    void addItem(Cart cart,Item item);
    double calculateTotal(Cart cart);

}
