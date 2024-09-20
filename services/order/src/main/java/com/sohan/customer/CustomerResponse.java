package com.sohan.customer;

public record CustomerResponse(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}
