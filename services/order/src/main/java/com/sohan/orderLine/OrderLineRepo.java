package com.sohan.orderLine;


import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepo extends JpaRepository<OrderLine,Integer> {
}
