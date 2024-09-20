package com.sohan.payment.payment;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
