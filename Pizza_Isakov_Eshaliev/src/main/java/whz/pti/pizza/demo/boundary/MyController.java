package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.domain.Pizza;
import whz.pti.pizza.demo.domain.PizzaSize;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.ItemRepository;
import whz.pti.pizza.demo.domain.repositories.PizzaRepository;
import whz.pti.pizza.demo.security.boundary.CurrentUserControllerAdvice;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.service.CartService;
import whz.pti.pizza.demo.service.CartServiceImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Optional;

@Controller
@Slf4j
public class MyController {


    @Autowired
    CurrentUserControllerAdvice currentUserControllerAdvice;
    @Autowired
    PizzaRepository pizzaRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    ItemRepository itemRepo;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CartRepository cartRepo;

    @GetMapping("/home")
    public String home(Model model){
        for (PizzaSize value : PizzaSize.values()) {
            model.addAttribute(value.toString().toLowerCase(),value);
        }
        model.addAttribute("listAllPizzas",pizzaRepo.findAll());
        model.addAttribute("amountPizzas",cartService.getQuantity());
        model.addAttribute("totalPrice",cartService.calculateTotal());
        return "index";
    }

    @PostMapping
    public String addPizza(@RequestParam Integer quantity,
                           @RequestParam Long pizzaId,
                           @RequestParam String pizzaSizeCost,
                           Authentication auth){
        User user = currentUserControllerAdvice
                    .getCurrentUser(auth)
                    .getUser();
        Customer customer = customerRepo
                .getByLoginName(user.getLoginName());

        if (quantity > 0 && customer != null) {
            Optional<Pizza> pizza = pizzaRepo.findById(pizzaId);
            if (pizza.isPresent()) {
                    Cart cart;
                try{
                    cart = cartRepo.getByCustomer(customer);
                }
                catch (NullPointerException e){
                    cart = new Cart();
                    cart.setCustomer(customer);
                }
                //parse
                pizzaSizeCost = pizzaSizeCost.replace("/","");
                String[] sizeCost = pizzaSizeCost.split("\\s+");
                String size = sizeCost[0];
                //check and updated quantity if already present
                for (Item cartItem : cart.getItems()) {
                    if (cartItem.getPizza().getId() == pizza.get().getId() &&
                        cartItem.getPizzaSize() == PizzaSize.valueOf(size)) {
                        cartItem.setQuantity(cartItem.getQuantity()+quantity);
                        return "redirect:/home";
                    }
                }
                Item item = new Item();
                item.setQuantity(quantity);
                item.setPizzaSize(PizzaSize.valueOf(size));
                item.setPizza(pizza.get());
                cart.addItem(item);

                itemRepo.save(item);
                cartService.addItem(item);
            }

        }

        return "redirect:/home";
    }




}
