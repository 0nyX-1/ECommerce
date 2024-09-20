package com.sohan.orderLine;

import com.sohan.order.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Order order;

    private Integer productId;
    private double quantity;
}
