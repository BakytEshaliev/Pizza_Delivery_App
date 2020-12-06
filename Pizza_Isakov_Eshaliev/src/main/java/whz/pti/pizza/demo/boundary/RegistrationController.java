package whz.pti.pizza.demo.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import whz.pti.pizza.demo.domain.repositories.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    UserRepository userRepo;
    @GetMapping
    public String registerForm(){
        return "registration";
    }

    @PostMapping
    public void processRegistration(RegistrationFrom form){
//        userRepo.save()
    }
}
