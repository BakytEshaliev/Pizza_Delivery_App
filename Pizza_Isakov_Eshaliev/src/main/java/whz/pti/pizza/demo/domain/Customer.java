package whz.pti.pizza.demo.domain;

import lombok.*;
import whz.pti.pizza.demo.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Customer extends BaseEntity<Long> {
//    @Id
//    @GeneratedValue
//    private Long id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    @ManyToMany(mappedBy = "customers")
    private List<DeliveryAddress> deliveryAddress = new ArrayList<>();
}
