package com.sohan.customer.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@EqualsAndHashCode(callSuper = true)
public class CustomerNotFoundException extends RuntimeException {
   public CustomerNotFoundException(String message) {
      super(message);
   }
}
