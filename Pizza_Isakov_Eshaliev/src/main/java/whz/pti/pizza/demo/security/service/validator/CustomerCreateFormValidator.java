package whz.pti.pizza.demo.security.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.xml.sax.SAXException;
import whz.pti.pizza.demo.domain.repositories.UserRepository;
import whz.pti.pizza.demo.security.domain.RegistrationForm;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;

@Component
public class CustomerCreateFormValidator implements Validator {

    @Autowired
    UserRepository userRepo;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RegistrationForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationForm form = (RegistrationForm) target;
         validateLoginName(errors,form);
         validatePasswords(errors,form);
    }


    private void validatePasswords(Errors errors, RegistrationForm form){
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password","unterschiedliche passwoerter eingegeben! vertippt?");
        }
    }
    private void validateLoginName(Errors errors, RegistrationForm form){
        if (userRepo.findByLoginName(form.getLoginName()) != null) {
            errors.reject("loginName", "nutzer mit diesem login name existiert bereits !!!");
        }
    }
}
