package com.sohan.kafka.Payment;

import java.math.BigDecimal;

public record PaymentConfirm(
    Integer id,
    String orderReference,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    String firstName,
    String lastName,
    String customerEmail
    )
{
}
