package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import whz.pti.pizza.demo.domain.Pizza;
import whz.pti.pizza.demo.domain.PizzaSize;
import whz.pti.pizza.demo.domain.repositories.PizzaRepository;

import java.util.Optional;

@Controller
@Slf4j
public class MyController {

    @Autowired
    PizzaRepository pizzaRepo;



    @GetMapping("/home")
    public String home(Model model){
        for (PizzaSize value : PizzaSize.values()) {
            model.addAttribute(value.toString().toLowerCase(),value);
        }
        model.addAttribute("listAllPizzas",pizzaRepo.findAll());
        return "index";
    }

    @PostMapping
    public String addPizza( @RequestParam Integer quantity,
                            @RequestParam Long pizzaId,
                            @RequestParam String pizzaSizeCost,
                            Model model){
        log.info("----------------------");
        Optional<Pizza> pizza = pizzaRepo.findById(pizzaId);
        System.out.println(quantity);
        pizza.ifPresent(System.out::println);
        pizzaSizeCost = pizzaSizeCost.replace("/","");
        String[] sizeCost = pizzaSizeCost.split("\\s+");
        String size = sizeCost[0];
        String cost = sizeCost[1];
        System.out.println(size+" "+cost);
        log.info("----------------------");
        return "redirect:/home";
    }




}
