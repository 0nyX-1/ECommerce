package com.sohan.order.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody OrderRequest orderRequest
    ){
        return new ResponseEntity<Integer>(
                orderService.createOrderLines(orderRequest),
                HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
       return new ResponseEntity<List<OrderResponse>>(
               orderService.findAll(),HttpStatus.OK
       );
    }
    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ){
        return new ResponseEntity<OrderResponse>(
                orderService.findById(orderId),HttpStatus.OK
        );
    }
}
