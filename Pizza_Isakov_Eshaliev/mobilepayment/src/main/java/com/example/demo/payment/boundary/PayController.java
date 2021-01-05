package com.example.demo.payment.boundary;

import com.example.demo.payment.model.PayCustomer;
import com.example.demo.payment.model.State;
import com.example.demo.payment.service.PayCustomerService;
import com.example.demo.payment.service.dto.AccountResponseDTO;
import com.example.demo.payment.service.dto.PayCustomerResponseDTO;
import com.example.demo.payment.service.dto.PaymentDTO;
import com.example.demo.payment.service.dto.StateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
@Slf4j
public class PayController {

    @Autowired
    PayCustomerService payCustomerService;

    @PostMapping("/{customerId}/payment")
    public ResponseEntity<?> pay(@PathVariable String customerId,
                                 @RequestBody PaymentDTO paymentDTO) {

        double amount = paymentDTO.getAmount();

        boolean response = payCustomerService.transfer(customerId, amount);
        if (response) {
            log.info("payment " + amount);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new AccountResponseDTO("Transfer ist erfolgreich durchgefuehrt"));
        } else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AccountResponseDTO("transferNotAllowed"));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getPayCustomer(@PathVariable String customerId) {
        PayCustomer payCustomer = payCustomerService.getPayCustomer(customerId);
        return ResponseEntity.ok(new PayCustomerResponseDTO(payCustomer));
    }

    @GetMapping("/{customerId}/account")
    public ResponseEntity<?> getBalance(@PathVariable String customerId) {
        State state = payCustomerService.getState(customerId);
        if (state == State.available) {
            double balance = payCustomerService.getAccBalanceByName(customerId);
            return ResponseEntity.ok(new AccountResponseDTO("Kontostand betraegt " + balance));
        } else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AccountResponseDTO("transferNotAllowed"));
    }

    @PutMapping("/{customerId}/opened")
    public ResponseEntity<?> openAcc(@PathVariable String customerId) {
        payCustomerService.openAccount(customerId);
        return ResponseEntity.ok(new AccountResponseDTO("Konto steht nun zur Verfuegung"));
    }

    @DeleteMapping("/{customerId}/deleted")
    public ResponseEntity<?> deleteUser(@PathVariable String customerId){
        payCustomerService.deleteUser(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new AccountResponseDTO("Nutzer und Konto ist nun geloescht"));
    }

    @PutMapping(value = "/{customerId}/suspended",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeUserToSuspend(@PathVariable String customerId,
                                                 @RequestBody StateDTO stateDTO){
        if(stateDTO.getState() == State.suspend){
            payCustomerService.changeToSuspend(customerId);
            return ResponseEntity.ok(new AccountResponseDTO("suspended"));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AccountResponseDTO("Falscher Zustand eingegeben"));
    }
}
