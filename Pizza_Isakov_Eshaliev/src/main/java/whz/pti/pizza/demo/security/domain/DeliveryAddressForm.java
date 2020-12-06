package whz.pti.pizza.demo.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class DeliveryAddressForm {
    @NotEmpty
    String street;

    @NotEmpty
    String hauseNumber;

    @NotEmpty
    String town;

    @NotEmpty
    String postalCode;
}
