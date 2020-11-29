package whz.pti.pizza.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.security.domain.Customer;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart extends BaseEntity<Long> {
//    @Id @GeneratedValue
//    private Long id;

    private int quantity;
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    //  private List<Item> items = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "id")
    private Map<Long,Item> items = new HashMap<>();
}
