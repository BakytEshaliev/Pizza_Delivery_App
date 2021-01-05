package whz.pti.pizza.demo.service;

import whz.pti.pizza.demo.service.dto.PayActionResponseDTO;

public interface SmmpService {
    PayActionResponseDTO doAction(String token, String customer, double amount);
}
