package whz.pti.pizza.demo.service.dto;

import java.io.Serializable;

public class TransferDTO implements Serializable{


    double amount;

    public TransferDTO() {
    }

    public TransferDTO(double amount) {

        this.amount = amount;
    }


    public double getAmount() {
        return amount;
    }
}
