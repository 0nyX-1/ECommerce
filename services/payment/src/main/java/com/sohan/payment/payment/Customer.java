package com.sohan.payment.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public record Customer(
       Integer id,

//       @NotNull(message = "FIRst name is required")
       String firstName,

//       @NotNull(message = "Last Name is required")
       String lastName,

//       @NotNull(message = "Email is required")
       @Email(message = "The customer is not currently formatted")
       String email
) {
}
