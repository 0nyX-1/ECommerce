package com.sohan.kafka.Payment;

import java.math.BigDecimal;

public  record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
){
}
