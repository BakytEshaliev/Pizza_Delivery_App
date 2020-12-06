package whz.pti.pizza.demo.security.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegistrationForm {
    @NotEmpty
    private String loginName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordRepeated;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
}
