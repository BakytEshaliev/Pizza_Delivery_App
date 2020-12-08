package whz.pti.pizza.demo.security.service.user;

import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.security.domain.Customer;

import java.util.List;

public interface CartService {

    void addItem(Cart cart,Item item);
    double calculateTotal(Cart cart);

}
