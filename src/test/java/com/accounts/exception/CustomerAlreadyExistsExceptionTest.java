package com.accounts.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerAlreadyExistsExceptionTest {

    @Test
    void testCustomerAlreadyExistsException() {
        String errorMessage = "Customer already exists";

        CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }
}
