package com.sohan.customer.customer;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
@Validated
public class Address {
    private String street;
    private Integer houseNumber;
    private String zipCode;

}
