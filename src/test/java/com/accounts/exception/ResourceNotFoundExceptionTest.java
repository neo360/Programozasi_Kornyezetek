package com.accounts.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ResourceNotFoundExceptionTest {

    @Test
    void testResourceNotFoundException() {
        String resourceName = "Customer";
        String fieldName = "id";
        String fieldValue = "123";

        String expectedMessage = String.format("%s not found with the given input data %s : '%s'", resourceName, fieldName, fieldValue);

        ResourceNotFoundException exception = new ResourceNotFoundException(resourceName, fieldName, fieldValue);

        assertEquals(expectedMessage, exception.getMessage());
    }
}
