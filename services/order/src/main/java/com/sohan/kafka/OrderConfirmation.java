package com.sohan.kafka;

import com.sohan.customer.CustomerResponse;
import com.sohan.order.order.PaymentMethod;
import com.sohan.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail,
        List<PurchaseResponse> products
) {
}
