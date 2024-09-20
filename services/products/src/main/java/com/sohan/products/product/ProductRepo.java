package com.sohan.products.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products,Integer> {
    List<Products> findAllByIdInOrderById(List<Integer> productId);
}
