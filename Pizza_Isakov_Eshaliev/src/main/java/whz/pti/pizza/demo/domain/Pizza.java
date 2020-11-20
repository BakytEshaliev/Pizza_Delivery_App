package whz.pti.pizza.demo.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import whz.pti.pizza.demo.common.BaseEntity;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Pizza extends BaseEntity<Long>{
    //    @Id
//    @GeneratedValue
//    private Long id;
    private String name;
    private double priceLarge;
    private double priceMedium;
    private double priceSmall;

}