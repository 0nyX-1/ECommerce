package com.sohan.order.order;

import com.sohan.customer.CustomerClient;
import com.sohan.customer.CustomerResponse;
import com.sohan.exception.BusinessException;
import com.sohan.kafka.OrderConfirmation;
import com.sohan.kafka.OrderProducer;
import com.sohan.orderLine.OrderLineRequest;
import com.sohan.orderLine.OrderLineService;
import com.sohan.payment.PaymentRequest;
import com.sohan.payment.PaymenttClient;
import com.sohan.product.ProductClient;
import com.sohan.product.PurchaseRequest;
import com.sohan.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
//    private final PaymentClient paymentClient;
    private final PaymenttClient paymenttClient;

    public CustomerResponse customerResponse(OrderRequest orderRequest){
        //check the customer or not
        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot Create the order. No customer Exists with provided ID"));
        if(customerResponse==null) throw new RuntimeException("Null");
        return customerResponse;
    }

    public List<PurchaseResponse> purchaseProductResponseList(OrderRequest orderRequest){
        //purchase the product--> product-microservice
        System.out.println("error here");
        List<PurchaseResponse> purchaseProductResponseList =
                productClient.purchaseProducts(orderRequest.products());
        return purchaseProductResponseList;
    }

    public Order createOrder(OrderRequest orderRequest){
        //persist order
        return orderRepository.save(orderMapper.toOrder(orderRequest));

    }

    public Integer createOrderLines(OrderRequest orderRequest) {
        CustomerResponse customerResponse = customerResponse(orderRequest);
        List<PurchaseResponse> purchaseProductResponseList = purchaseProductResponseList(orderRequest);
        Order order = createOrder(orderRequest);

        //persist order lines
        System.out.println("ERROR THROWN BEFORE PURCHASE REQUEST");
        for(PurchaseRequest purchaseRequest : orderRequest.products()){
             orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        System.out.println("ERROR THROWN AFTER PURCHASE REQUEST");

        //start payment method

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (PurchaseResponse purchaseResponse : purchaseProductResponseList) {
            BigDecimal bigDecimalQuantity = BigDecimal.valueOf(purchaseResponse.quantity());
            BigDecimal bigDecimalPrice = purchaseResponse.price();
            BigDecimal multipliedPrice = bigDecimalQuantity.multiply(bigDecimalPrice);
            totalPrice = totalPrice.add(multipliedPrice);
        }


        paymenttClient.createPayment(
                PaymentRequest.builder()
                        .orderId(order.getId())
                        .orderReference(orderRequest.reference())
                        .amount(totalPrice)
                        .paymentMethod(orderRequest.paymentMethod())
                        .customerFirstName(customerResponse.firstName())
                        .customerLastName(customerResponse.lastName())
                        .customerEmail(customerResponse.email())
                        .build()

        );


        //send the order confirmation --> notification (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        totalPrice,
                        orderRequest.paymentMethod(),
                        customerResponse.firstName(),
                        customerResponse.lastName(),
                        customerResponse.email(),
                        purchaseProductResponseList
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(() -> new EntityNotFoundException("" +
//                        "Not found"));
//
//        return new OrderResponse(
//                order.getId(),
//                order.getReference(),
//                order.getPrice(),
//                order.getPaymentMethod(),
//                order.getCustomerId()
//        );

        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(()-> new EntityNotFoundException(
                        "Not found with that ID"
                ));
    }
}
