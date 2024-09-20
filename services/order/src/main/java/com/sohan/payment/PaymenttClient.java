package com.sohan.payment;

import com.sohan.product.PurchaseRequest;
import com.sohan.product.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymenttClient {
    private final RestTemplate restTemplate;

    public Integer createPayment(
            PaymentRequest paymentRequest
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(paymentRequest, headers);
        ParameterizedTypeReference<Integer> responseType = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<Integer> responseEntity =
                restTemplate.exchange(
                        "http://localhost:8030/api/v1/payment/create",
                        HttpMethod.POST,
                        requestEntity,
                        responseType
                );
        return responseEntity.getBody();
    }
}
