package whz.pti.pizza.demo.security.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.UserRepository;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.RegistrationForm;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.security.service.validator.CustomerCreateFormValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    @Autowired
    UserRepository userRepo;
    @Autowired
    CustomerRepository customerRepo;
    @Autowired
    CustomerCreateFormValidator customerCreateFormValidator;
//    @Autowired

    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @InitBinder("myform")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(customerCreateFormValidator);
    }

    @GetMapping
    public String getRegisterPage(){
        return "registration";
    }



    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("myform") RegistrationForm form,
                                      BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("error",bindingResult.getGlobalError().getDefaultMessage());
            return "registration";
        }
        User user = new User();
        user.setLoginName(form.getLoginName());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        userRepo.save(user);
        Customer customer = new Customer();
        customer.setLoginName(form.getLoginName());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setUser(user);
        customerRepo.save(customer);
//        Cart cart = new Cart();
//        cart.setCustomer(customer);
//        ca
        log.info("Register "+customer);
        log.info("Register "+user);
        return "redirect:/login";
    }
}
