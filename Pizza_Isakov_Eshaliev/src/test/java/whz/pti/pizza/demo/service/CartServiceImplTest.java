package whz.pti.pizza.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import whz.pti.pizza.demo.domain.Item;
import whz.pti.pizza.demo.domain.Pizza;
import whz.pti.pizza.demo.domain.PizzaSize;
import whz.pti.pizza.demo.security.service.user.CartService;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
class CartServiceImplTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();

    }
    @Test
    void testCalculateTotalPrice() {
        Item item1 = new Item();
        Item item2 = new Item();
        var pizza1 = new Pizza();
        pizza1.setName("mozarella");
        pizza1.setPriceLarge(5.00);
        var pizza2 = new Pizza();
        pizza2.setName("vegi");
        pizza2.setPriceMedium(4.5);

        item1.setPizza(pizza1);
        item1.setPizzaSize(PizzaSize.Large);
        item1.setQuantity(2);
        item2.setPizza(pizza2);
        item2.setPizzaSize(PizzaSize.Medium);
        item2.setQuantity(2);

        when(cartService.getItems()).thenReturn(List.of(item1,item2));

        double expected = cartService.calculateTotal();
        Assertions.assertThat(expected).isEqualTo(19.0);
    }
}