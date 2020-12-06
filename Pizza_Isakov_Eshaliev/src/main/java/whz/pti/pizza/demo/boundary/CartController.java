package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.service.CartService;

@Controller
@Slf4j
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartRepository cartRepo;

    @GetMapping("/cart")
    public String cartPage(Model model){
        model.addAttribute("items",cartService.getItems());
        model.addAttribute("totalPrice",cartService.calculateTotal());
        return "cart";
    }
}
