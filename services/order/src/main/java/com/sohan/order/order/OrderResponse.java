package com.sohan.order.order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id, //orderId
        String reference,
        BigDecimal price,
        PaymentMethod paymentMethod,
        Integer customerId
) {
}
