package com.sohan.products.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NonNull;

import java.math.BigDecimal;

public record ProductRequest(

        Integer id,

        @NotNull(message = "Product name is required")
        String nameProduct,

        @NotNull(message = "Product Description is required")
        String description,

        @NotNull(message = "Price is required")
        @Positive(message = "Price should be positive")
        BigDecimal price,

        @NotNull(message = "Product Category is required")
        Integer categoryId,

        @Positive(message = "Quanitity should be positive")
        double availableQuantity
) {
}
