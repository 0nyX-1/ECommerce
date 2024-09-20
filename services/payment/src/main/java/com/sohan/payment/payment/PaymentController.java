package com.sohan.payment.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@Slf4j
public class PaymentController {

    private final PaymentService service;

    @PostMapping("/create")
    public ResponseEntity<Integer> createPayment(
            @RequestBody @Valid PaymentRequest paymentRequest
    ){
        log.info("Processing");
        return new ResponseEntity<Integer>(
                service.createPayment(paymentRequest), HttpStatus.OK
        );
    }

}
