package com.sohan.orderLine;

import com.sohan.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepo orderLineRepo;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = mapper.toOrderLine(orderLineRequest);
        return orderLineRepo.save(orderLine).getId();

    }
}
