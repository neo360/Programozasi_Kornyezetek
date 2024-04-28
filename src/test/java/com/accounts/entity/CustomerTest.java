package com.accounts.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerTest {

    @Test
    void testCustomerEntity() {
        Long customerId = 123L;
        String name = "John Doe";
        String email = "john@example.com";
        String mobileNumber = "1234567890";

        Customer customer = new Customer(customerId, name, email, mobileNumber);

        assertEquals(customerId, customer.getCustomerId());
        assertEquals(name, customer.getName());
        assertEquals(email, customer.getEmail());
        assertEquals(mobileNumber, customer.getMobileNumber());
    }

    @Test
    void testEmptyCustomerEntity() {
        Customer customer = new Customer();

        assertNull(customer.getCustomerId());
        assertNull(customer.getName());
        assertNull(customer.getEmail());
        assertNull(customer.getMobileNumber());
    }
}

