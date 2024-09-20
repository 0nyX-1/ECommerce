package com.sohan.products.product;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,
        String nameProduct,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription

) {
}
