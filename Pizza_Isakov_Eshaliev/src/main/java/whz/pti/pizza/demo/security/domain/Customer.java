package whz.pti.pizza.demo.security.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.domain.DeliveryAddress;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity<Long>{

    private String loginName;
    private String passwordHash;
    private String firstName;
    private String lastName;
    @ManyToMany(mappedBy = "customers")
    private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
    Role role = Role.USER;
}
