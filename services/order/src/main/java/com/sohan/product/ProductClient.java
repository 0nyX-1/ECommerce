package com.sohan.product;

import com.sohan.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductClient {
    @Value("${application.config.products-url}")
    private String productsUrl;

    private final RestTemplate restTemplate;


    public List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseResponse>> responseType = new ParameterizedTypeReference<>(){};
        ResponseEntity<List<PurchaseResponse>> responseEntity=
        restTemplate.exchange(
                "http://localhost:8060/api/v1/products/purchase",
                HttpMethod.POST,
                requestEntity,
                responseType
        );
        return responseEntity.getBody();
    }
}
