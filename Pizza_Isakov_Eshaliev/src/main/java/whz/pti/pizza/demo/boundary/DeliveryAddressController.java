package whz.pti.pizza.demo.boundary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import whz.pti.pizza.demo.domain.DeliveryAddress;
import whz.pti.pizza.demo.domain.repositories.CartRepository;
import whz.pti.pizza.demo.domain.repositories.CustomerRepository;
import whz.pti.pizza.demo.domain.repositories.DeliveryAddressRepository;
import whz.pti.pizza.demo.security.boundary.CurrentUserControllerAdvice;
import whz.pti.pizza.demo.security.domain.Customer;
import whz.pti.pizza.demo.security.domain.DeliveryAddressForm;
import whz.pti.pizza.demo.security.domain.RegistrationForm;
import whz.pti.pizza.demo.security.domain.User;
import whz.pti.pizza.demo.security.service.user.CartService;
//import whz.pti.pizza.demo.security.service.user.DeliveryAddressService;

import javax.validation.Valid;

@Controller
@Slf4j
public class DeliveryAddressController {
//    @Autowired
//    DeliveryAddressService deliveryAddressService;
    @Autowired
    DeliveryAddressRepository deliveryAddressRepository;
    @Autowired
    CurrentUserControllerAdvice currentUserControllerAdvice;
    @Autowired
    CustomerRepository customerRepository;


    @GetMapping("/deliveryAddress")
    public String DeliveryAddressPage(Model model,
                                      Authentication auth){
//        User user = currentUserControllerAdvice
//                .getCurrentUser(auth)
//                .getUser();
//        Customer customer = customerRepository
//                .getByLoginName(user.getLoginName());
        model.addAttribute("listAllDeliveryAddresses", deliveryAddressRepository.findAll());
        return "deliveryAddress";
    }

    @GetMapping("/newAddress")
    public String NewAddressPage(Model model, Authentication auth){
        DeliveryAddress da = new DeliveryAddress();
        return "newAddress";
    }

    @PostMapping("/newAddress")
    public String processRegistration(@Valid @ModelAttribute("daForm") DeliveryAddressForm form,
                                      BindingResult bindingResult, Authentication auth,
                                      Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("error",bindingResult.getGlobalError().getDefaultMessage());
            return "newAddress";
        }

        User user = currentUserControllerAdvice
                .getCurrentUser(auth)
                .getUser();
        Customer customer = customerRepository
                .getByLoginName(user.getLoginName());

        DeliveryAddress da = new DeliveryAddress(form.getStreet(), form.getHauseNumber(),
                form.getTown(),form.getPostalCode());
        da.addCustomer(customer);
        customer.addDeliveryAddress(da);

        deliveryAddressRepository.save(da);

        log.info("Add address "+da);
        log.info("Costumer "+customer);
        return "redirect:/deliveryAddress";
    }
}