package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.security.boundary.CurrentUserControllerAdvice;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.security.service.user.CartService;

@Controller
@Slf4j
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    CurrentUserControllerAdvice currentUserControllerAdvice;
    @Autowired
    CustomerRepository customerRepo;

    @GetMapping("/cart")
    public String cartPage(Model model, Authentication auth){
        User user = currentUserControllerAdvice
                .getCurrentUser(auth)
                .getUser();
        Customer customer = customerRepo
                .getByLoginName(user.getLoginName());
        if (customer == null){
            return "redirect:/home";
        }
        Cart cart = cartRepo.getByCustomer(customer);
        model.addAttribute("items",cart.getItems());
        double price = cartService.calculateTotal(cart);
        log.info("=======cartPage price "+price);
        model.addAttribute("totalPrice", price);
        return "cart";
    }
    @PostMapping("/cart")
    public String deleteItemFromCart(@RequestParam Long itemId,Authentication auth){
        User user = currentUserControllerAdvice
                .getCurrentUser(auth)
                .getUser();
        Customer customer = customerRepo
                .getByLoginName(user.getLoginName());
        Cart cart = cartRepo.getByCustomer(customer);
        log.info("======cartPage Item id "+itemId);
        log.info("======cartPage cartItems "+cart.getItems().size());
        cart.getItems().removeIf(item -> {
            if(item.getId()==itemId){
                cart.setQuantity(cart.getQuantity()-1);
                return true;
            }
            return false;
        });
        cartRepo.save(cart);

        return "redirect:/cart";
    }
}
