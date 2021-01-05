package com.example.demo.payment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {

    int amount;

    public PaymentDTO(int amount) {
        this.amount = amount;
    }
}
