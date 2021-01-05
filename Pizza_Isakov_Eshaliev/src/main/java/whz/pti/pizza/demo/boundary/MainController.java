package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.pizza.demo.common.CurrentUserUtil;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.domain.Pizza;
import whz.pti.pizza.demo.domain.PizzaSize;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.ItemRepository;
import whz.pti.pizza.demo.domain.repositories.PizzaRepository;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.security.service.user.CartService;
import whz.pti.pizza.demo.service.SmmpService;

import java.util.Optional;

@Controller
@Slf4j
public class MainController {


    @Autowired
    PizzaRepository pizzaRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    ItemRepository itemRepo;
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepo;
    @Autowired
    SmmpService smmpService;

    @GetMapping("/home")
    public String home(Model model){
        for (PizzaSize value : PizzaSize.values()) {
            model.addAttribute(value.toString().toLowerCase(),value);
        }
        User user = CurrentUserUtil.getCurrentUser(model)
                .getUser();
        Customer customer = customerRepo
                .getByLoginName(user.getLoginName());
        if (customer == null){
            model.addAttribute("listAllPizzas",pizzaRepo.findAll());
            model.addAttribute("amountPizzas", 0);
            model.addAttribute("totalPrice", 0);

            return "index";
        }
        Cart cart = cartRepo.getByCustomer(customer);


        model.addAttribute("balance",smmpService.doAction("balance",customer.getLoginName(),-1).getDescription());
        model.addAttribute("listAllPizzas",pizzaRepo.findAll());

        int quantity = cart.getQuantity();
//        log.info("===quantity "+quantity);
        model.addAttribute("amountPizzas", quantity);
        double price = cartService.calculateTotal(cart);
//        log.info("===price "+price);
        model.addAttribute("totalPrice", price);
        return "index";
    }

    @PostMapping
    public String addPizza(@RequestParam Integer quantity,
                           @RequestParam Long pizzaId,
                           @RequestParam String pizzaSizeCost,
                           Model model){
        User user = CurrentUserUtil.getCurrentUser(model)
                    .getUser();
        Customer customer = customerRepo
                .getByLoginName(user.getLoginName());
        if (customer == null){
            return "redirect:/home";
        }

        if (quantity > 0 && customer != null) {
            Optional<Pizza> pizza = pizzaRepo.findById(pizzaId);
            if (pizza.isPresent()) {

                Cart cart = cartRepo.getByCustomer(customer);

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
                itemRepo.save(item);

                cartService.addItem(cart,item);
                //update entity
                cartRepo.save(cart);
                log.info("=======Cart "+cart);
            }

        }

        return "redirect:/home";
    }




}
