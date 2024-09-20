package com.sohan.products.product;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.sohan.products.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameProduct;
    private String description;
    private double availableQuantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Category category;
}
