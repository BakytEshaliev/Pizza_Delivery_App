package whz.pti.pizza.demo.domain;
import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.security.domain.Customer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor

public class Ordered extends BaseEntity<Long> {
//    @Id
//    @GeneratedValue
//    private Long id;

    private int numberOfItems;

    @ManyToOne
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "orderedItem_id")
    private List<OrderedItem> orderedItems = new ArrayList<>();
}
