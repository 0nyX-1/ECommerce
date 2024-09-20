package com.sohan.payment;

import com.sohan.customer.CustomerResponse;
import com.sohan.order.order.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
