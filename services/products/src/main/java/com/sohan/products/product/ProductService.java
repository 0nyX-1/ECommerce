package com.sohan.products.product;

import com.sohan.products.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper mapper;

    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> productRequest) {
        List<Integer> productId = productRequest.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        List<Products> productsListFromDatabase = productRepo.findAllByIdInOrderById(productId);

        if(productId.size()!=productsListFromDatabase.size()){
            throw new ProductPurchaseException("One or more products doesn't exist");
        }

        List<ProductPurchaseRequest> storedRequest = productRequest.stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        ArrayList<ProductPurchaseResponse> purchaseProducts = new ArrayList<>();

        for(int i =0; i<productsListFromDatabase.size(); i++){
            Products products = productsListFromDatabase.get(i);
            ProductPurchaseRequest productPurchaseRequest = storedRequest.get(i);
            if(products.getAvailableQuantity()<productPurchaseRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product ");
            }
            double newAvailableQuantity = products.getAvailableQuantity() - productPurchaseRequest.quantity();
            products.setAvailableQuantity(newAvailableQuantity);
            productRepo.save(products);

            purchaseProducts.add(mapper.toProductPurchaseResponse(products,productPurchaseRequest.quantity()));
        }
        return purchaseProducts;
    }

    public Integer createProduct(ProductRequest productRequest) {
        Products product = mapper
                .toProduct(productRequest);
        return productRepo.save(product).getId();
    }

    public ProductResponse findById(Integer productId) {
        return productRepo.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException(
                        "Product with that Id not found"
                ));
    }

    public List<ProductResponse> findAllProducts() {
        return productRepo
                .findAll()
                .stream()
                .map(mapper::toProductResponse)
                .toList();
    }
}
