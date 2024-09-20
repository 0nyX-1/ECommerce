package com.sohan.orderLine;

public record OrderLineRequest(
        Integer orderId,
        Integer productId,
        double quantity
) {
}
