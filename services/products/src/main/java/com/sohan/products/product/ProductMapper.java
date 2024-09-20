package com.sohan.products.product;

import com.sohan.products.category.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Products toProduct(@NotNull ProductRequest productRequest) {
        return Products.builder()
                .nameProduct(productRequest.nameProduct())
                .id(productRequest.id())
                .price(productRequest.price())
                .availableQuantity(productRequest.availableQuantity())
                .category(Category.builder().id(productRequest.categoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(@NotNull Products products) {
        return new ProductResponse(
                products.getId(),
                products.getNameProduct(),
                products.getDescription(),
                products.getAvailableQuantity(),
                products.getPrice(),
                products.getCategory().getId(),
                products.getCategory().getName(),
                products.getCategory().getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(@NotNull Products products, double quantity) {
        return new ProductPurchaseResponse(
                products.getId(),
                products.getNameProduct(),
                products.getDescription(),
                products.getPrice(),
                quantity
        );
    }
}
