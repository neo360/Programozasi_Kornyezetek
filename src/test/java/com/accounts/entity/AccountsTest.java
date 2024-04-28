package com.accounts.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AccountsTest {

    @Test
    void testAccountsEntity() {
        Long customerId = 123L;
        Long accountNumber = 4567890L;
        String accountType = "Savings";
        String branchAddress = "123 Main Street, New York";

        Accounts accounts = new Accounts(customerId, accountNumber, accountType, branchAddress);

        assertEquals(customerId, accounts.getCustomerId());
        assertEquals(accountNumber, accounts.getAccountNumber());
        assertEquals(accountType, accounts.getAccountType());
        assertEquals(branchAddress, accounts.getBranchAddress());
    }

    @Test
    void testEmptyAccountsEntity() {
        Accounts accounts = new Accounts();

        assertNull(accounts.getCustomerId());
        assertNull(accounts.getAccountNumber());
        assertNull(accounts.getAccountType());
        assertNull(accounts.getBranchAddress());
    }
}
