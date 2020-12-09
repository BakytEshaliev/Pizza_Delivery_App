package whz.pti.pizza.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.security.domain.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cart extends BaseEntity<Long> {

    private int quantity = 0;
    @OneToOne(cascade = {CascadeType.DETACH})
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Item> items = new ArrayList<>();

}
