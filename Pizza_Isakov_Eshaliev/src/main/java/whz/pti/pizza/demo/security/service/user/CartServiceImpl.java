package whz.pti.pizza.demo.security.service.user;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.security.domain.Customer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Service
@SessionScope
public class CartServiceImpl implements CartService{

    @Autowired
    CartRepository cartRepo;

    @Override
    public void addItem(Cart cart,Item item) {
        cart.getItems().add(item);
        cart.setQuantity(cart.getQuantity()+1);
    }


    @Override
    public double calculateTotal(Cart cart) {
        double total = 0;
        for (Item item : cart.getItems()) {
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

}
