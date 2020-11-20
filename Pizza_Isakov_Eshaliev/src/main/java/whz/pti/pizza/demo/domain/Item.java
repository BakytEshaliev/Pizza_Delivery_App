package whz.pti.pizza.demo.domain;
import lombok.*;
import whz.pti.pizza.demo.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Item extends BaseEntity<Long> {
//    @Id @GeneratedValue
//    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @Enumerated(value = EnumType.STRING)
    private PizzaSize pizzaSize;
}
