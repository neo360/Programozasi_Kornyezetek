package com.accounts.constants;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountsConstantsTest {

    @Test
    void testConstants() {
        assertEquals("Savings", AccountsConstants.SAVINGS);
        assertEquals("123 Main Street, New York", AccountsConstants.ADDRESS);
        assertEquals("201", AccountsConstants.STATUS_201);
        assertEquals("Account created successfully", AccountsConstants.MESSAGE_201);
        assertEquals("200", AccountsConstants.STATUS_200);
        assertEquals("Request processed successfully", AccountsConstants.MESSAGE_200);
        assertEquals("417", AccountsConstants.STATUS_417);
        assertEquals("Update operation failed. Please try again or contact Dev team", AccountsConstants.MESSAGE_417_UPDATE);
        assertEquals("Delete operation failed. Please try again or contact Dev team", AccountsConstants.MESSAGE_417_DELETE);
    }
}
