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

    private int numberOfItems;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<OrderedItem> orderedItems = new ArrayList<>();

    @OneToOne
    private DeliveryAddress deliveryAddress;

    public void addOrderedItem(OrderedItem orderedItem){
        orderedItems.add(orderedItem);
    }
}
