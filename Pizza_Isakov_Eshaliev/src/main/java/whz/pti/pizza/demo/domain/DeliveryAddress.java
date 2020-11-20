package whz.pti.pizza.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.pizza.demo.common.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeliveryAddress extends BaseEntity<Long> {
//    @Id @GeneratedValue
//    private Long id;
    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;

    @ManyToMany
    private List<Customer> customers = new ArrayList<>();
}
