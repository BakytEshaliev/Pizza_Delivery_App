package whz.pti.pizza.demo.security.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.domain.Cart;
import whz.pti.pizza.demo.domain.DeliveryAddress;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity<Long>{
    private String firstName;
    private String lastName;
    private String loginName;
    @OneToOne
    private User user;
    @ManyToMany(mappedBy = "customers")
    private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
}
