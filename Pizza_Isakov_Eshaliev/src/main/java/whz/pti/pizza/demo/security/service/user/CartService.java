package whz.pti.pizza.demo.security.service.user;

import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;

import java.util.List;

public interface CartService {
    void setCart(Cart cart);
    void addItem(Item item);
    Item getItemById(long id);
    int getQuantity();
    List<Item> getItems();
    double calculateTotal();
    Cart getCart();
    void clearCart();
}
