package com.sohan.products.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Validated ProductRequest productRequest
    ){
       return new ResponseEntity<Integer>(
               productService.createProduct(productRequest),
               HttpStatus.OK
       );
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseResponse(
            @RequestBody List<ProductPurchaseRequest> productRequest
    ){
        return new ResponseEntity<List<ProductPurchaseResponse>>(
                productService.purchaseProduct(productRequest),
                HttpStatus.OK
        );
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ){
        return new ResponseEntity<ProductResponse>(
                productService.findById(productId),
                HttpStatus.OK
        );
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductResponse>> findAllProducts(
    ){
        return new ResponseEntity<List<ProductResponse>>(
                productService.findAllProducts(),
                HttpStatus.OK
        );
    }

}