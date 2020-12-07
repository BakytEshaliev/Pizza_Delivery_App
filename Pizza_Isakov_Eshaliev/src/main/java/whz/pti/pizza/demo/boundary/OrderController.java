package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.domain.Ordered;
import whz.pti.pizza.demo.domain.OrderedItem;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.DeliveryAddressRepository;
import whz.pti.pizza.demo.domain.repositories.OrderedItemRepository;
import whz.pti.pizza.demo.domain.repositories.OrderedRepository;
import whz.pti.pizza.demo.security.boundary.CurrentUserControllerAdvice;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.security.service.user.CartService;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    CurrentUserControllerAdvice currentUserControllerAdvice;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    OrderedItemRepository orderedItemRepository;

    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;

    @Autowired
    CartService cartService;

    @PostMapping("/order")
    public String order(Authentication auth,
                      @RequestParam Long deliveryAddressId){
        User user = currentUserControllerAdvice
                .getCurrentUser(auth)
                .getUser();
        Customer customer = customerRepository
                .getByLoginName(user.getLoginName());

        Ordered ordered = new Ordered();
        ordered.setNumberOfItems(cartService.getQuantity());
        ordered.setCustomer(customer);
        ordered.setDeliveryAddress(deliveryAddressRepository.getById(deliveryAddressId));

        for (Item item : cartService.getItems()){
            OrderedItem orderedItem = new OrderedItem();
            orderedItem.setPizza(item.getPizza());
            orderedItem.setQuantity(item.getQuantity());
            orderedItem.setCustomer(customer);
            orderedItem.setPizzaSize(item.getPizzaSize());
            orderedItemRepository.save(orderedItem);
            ordered.addOrderedItem(orderedItem);
        }

        orderedRepository.save(ordered);
        cartService.clearCart();
        return "redirect:/home";
    }
}
