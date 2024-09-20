package com.sohan.product;

import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,

        @NotNull(message = "Product quantity is mandatory")
        double quantity
) {
}
