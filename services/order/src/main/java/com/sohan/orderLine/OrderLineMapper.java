package com.sohan.orderLine;

import com.sohan.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
      return OrderLine.builder()
              .quantity(orderLineRequest.quantity())
              .order(Order.builder().id(orderLineRequest.orderId()).build())
              .productId(orderLineRequest.productId())
              .build();
    }
}
