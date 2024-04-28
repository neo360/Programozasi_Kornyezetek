package com.accounts.dto;

import com.accounts.utils.ValidatorUtil;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountsDtoTest {
    private final Validator validator = ValidatorUtil.getValidator();

    @Test
    void testValidAccountsDto() {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("Savings");
        accountsDto.setBranchAddress("123 NewYork");

        Set<ConstraintViolation<AccountsDto>> violations = validator.validate(accountsDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidAccountsDto() {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(null);
        accountsDto.setAccountType("InvalidType");
        accountsDto.setBranchAddress("");

        Set<ConstraintViolation<AccountsDto>> violations = validator.validate(accountsDto);
        assertEquals(0, violations.size());
    }
}
