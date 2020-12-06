package whz.pti.pizza.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.security.domain.Customer;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class OrderedItem extends BaseEntity<Long> {
//    @Id
//    @GeneratedValue
//    private Long id;

    @ManyToOne
    private Pizza pizza;

    private String name;
    private int quantity;

    @ManyToOne
    private Customer customer;
    @Enumerated(value = EnumType.STRING)
    private PizzaSize pizzaSize;
}
