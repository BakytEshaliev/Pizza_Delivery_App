package whz.pti.pizza.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import whz.pti.pizza.demo.common.BaseEntity;
import whz.pti.pizza.demo.security.domain.Customer;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class DeliveryAddress extends BaseEntity<Long> {
//    @Id @GeneratedValue
//    private Long id;
    private String street;
    private String houseNumber;
    private String town;
    private String postalCode;

    @ManyToMany
    private List<Customer> customers = new ArrayList<>();

    public DeliveryAddress(String street, String houseNumber, String town, String postalCode){
        this.street = street;
        this.houseNumber = houseNumber;
        this.town = town;
        this.postalCode = postalCode;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
}
