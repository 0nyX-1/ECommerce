package com.sohan.kafka.Order;

public record Customer(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}
