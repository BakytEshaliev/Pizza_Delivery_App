package whz.pti.pizza.demo.security.service.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import whz.pti.pizza.demo.security.domain.DeliveryAddressForm;

@Component
public class DeliveryAddressCreateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DeliveryAddressForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DeliveryAddressForm form = (DeliveryAddressForm) target;
        validatePostalCode(errors, form);
    }

    public void validatePostalCode(Errors errors, DeliveryAddressForm form){
        try{
            Integer.parseInt(form.getPostalCode());
        } catch (NumberFormatException nfe){
            errors.reject("Postal code", "postal code should be int");
        }
    }
}
