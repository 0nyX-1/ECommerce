package com.sohan.order.order;

import com.sohan.orderLine.OrderLine;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_products")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reference;
    private BigDecimal price;

    //This one is Many to One relationship
    private Integer customerId;

    @Enumerated
    private PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderLine> orderLineList;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = false)
    private LocalDateTime lastModifiedDate;
}
