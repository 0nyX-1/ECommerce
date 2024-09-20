package com.sohan.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record CustomerResponse(
        Integer id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
