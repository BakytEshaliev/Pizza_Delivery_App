package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.pizza.demo.domain.*;
import whz.pti.pizza.demo.domain.repositories.*;
import whz.pti.pizza.demo.security.boundary.CurrentUserControllerAdvice;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.security.service.user.CartService;
import whz.pti.pizza.demo.service.dto.PayActionResponseDTO;

import java.util.ArrayList;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    CurrentUserControllerAdvice currentUserControllerAdvice;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepo;

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

        if (customer == null){
            return "redirect:/home";
        }
        Cart cart = cartRepo.getByCustomer(customer);

        DeliveryAddress deliveryAddress = deliveryAddressRepository.getById(deliveryAddressId);

        if (!deliveryAddress.getCustomers().contains(customer)){
            deliveryAddress.addCustomer(customer);
        }
        if (!customer.getDeliveryAddresses().contains(deliveryAddress)){
            customer.addDeliveryAddress(deliveryAddress);
        }

        Ordered ordered = new Ordered();
        ordered.setNumberOfItems(cart.getQuantity());
        ordered.setCustomer(customer);
        ordered.setDeliveryAddress(deliveryAddress);

        for (Item item : cart.getItems()){
            OrderedItem orderedItem = new OrderedItem();
            orderedItem.setPizza(item.getPizza());
            orderedItem.setQuantity(item.getQuantity());
            orderedItem.setCustomer(customer);
            orderedItem.setPizzaSize(item.getPizzaSize());
            orderedItemRepository.save(orderedItem);
            ordered.addOrderedItem(orderedItem);
        }
        return "redirect:/home";
    }
}
