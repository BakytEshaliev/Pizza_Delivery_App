//package whz.pti.pizza.demo.service;
//
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.springframework.web.context.annotation.SessionScope;
//import whz.pti.pizza.demo.domain.Cart;
//import whz.pti.pizza.demo.domain.DeliveryAddress;
//import whz.pti.pizza.demo.domain.Item;
//import whz.pti.pizza.demo.domain.PizzaSize;
//import whz.pti.pizza.demo.domain.repositories.CartRepository;
//import whz.pti.pizza.demo.domain.repositories.DeliveryAddressRepository;
//import whz.pti.pizza.demo.security.service.currentuser.CurrentUserDetailsService;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.util.List;
//
//@Service
//@SessionScope
//public class DeliveryAddressImpl implements DeliveryAddressService{
//
//    @Autowired
//    DeliveryAddressRepository deliveryAddressRepository;
//
//    private List<DeliveryAddress> deliveryAddresses;
//
//    @PostConstruct
//    private void init(){
//
//    }
//}
