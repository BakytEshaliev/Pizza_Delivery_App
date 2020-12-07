package whz.pti.pizza.demo.security.service.user;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.domain.repositories.CartRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepo;

    @Setter
    private Cart cart;

    @PostConstruct
    private void init(){
        cart = cartRepo.save(new Cart());
    }


    @Override
    public void addItem(Item item) {
        getCart().getItems().add(item);
        getCart().setQuantity(getCart().getQuantity()+1);
    }

    @Override
    public Item getItemById(long id) {
        for (Item item : getCart().getItems()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public int getQuantity() {
        return getCart().getQuantity();
    }

    @Override
    public List<Item> getItems() {
        return getCart().getItems();
    }

    @Override
    public double calculateTotal() {
        double total = 0;
        for (Item item : getCart().getItems()) {
            switch (item.getPizzaSize()) {
                case Large:
                    total += item.getPizza().getPriceLarge() * item.getQuantity();
                    break;
                case Small:
                    total += item.getPizza().getPriceSmall() * item.getQuantity();
                    break;
                case Medium:
                    total += item.getPizza().getPriceMedium() * item.getQuantity();
                    break;
            }

        }
        return total;
    }
    @PreDestroy
    public void preDestroy(){
      cartRepo.delete(cart);
    }
    @Override
    public Cart getCart() {
        return cart;
    }
    @Override
    public void clearCart(){
        cart.setItems(new ArrayList<>());
        cart.setQuantity(0);
    }
}
