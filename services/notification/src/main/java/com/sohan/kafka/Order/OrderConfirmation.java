package com.sohan.kafka.Order;

import com.sohan.kafka.Payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        Integer id,
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail,
        List<Product> products
) {
}
