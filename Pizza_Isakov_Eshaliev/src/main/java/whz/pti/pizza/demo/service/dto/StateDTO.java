package whz.pti.pizza.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateDTO {
    String state;

    public StateDTO(String state) {
        this.state = state;
    }
}
