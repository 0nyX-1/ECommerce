package com.sohan.order.order;

import com.sohan.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@Validated
public record OrderRequest(

        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @Positive(message = "Payment method should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer Id  should be present")
        Integer customerId,

        @NotEmpty(message = "You should at lease get one product")
        List<PurchaseRequest> products
){
}
