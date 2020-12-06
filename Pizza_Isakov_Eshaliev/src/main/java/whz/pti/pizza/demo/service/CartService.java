package whz.pti.pizza.demo.service;

import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;

import java.util.List;

public interface CartService {
    void addItem(Item item);
    Item getItemById(long id);
    int getQuantity();
    List<Item> getItems();
    double calculateTotal();
    Cart getCart();
}
