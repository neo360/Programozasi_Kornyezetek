package com.accounts.mapper;

import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AccountsMapperTest {

    @Test
    void testMapToAccountsDto() {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("Savings");
        accounts.setBranchAddress("123 Main Street, New York");

        AccountsDto accountsDto = new AccountsDto();

        AccountsDto mappedDto = AccountsMapper.mapToAccountsDto(accounts, accountsDto);

        assertNotNull(mappedDto);
        assertEquals(accounts.getAccountNumber(), mappedDto.getAccountNumber());
        assertEquals(accounts.getAccountType(), mappedDto.getAccountType());
        assertEquals(accounts.getBranchAddress(), mappedDto.getBranchAddress());
    }

    @Test
    void testMapToAccounts() {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("Savings");
        accountsDto.setBranchAddress("123 Main Street, New York");

        Accounts accounts = new Accounts();

        Accounts mappedEntity = AccountsMapper.mapToAccounts(accountsDto, accounts);

        assertNotNull(mappedEntity);
        assertEquals(accountsDto.getAccountNumber(), mappedEntity.getAccountNumber());
        assertEquals(accountsDto.getAccountType(), mappedEntity.getAccountType());
        assertEquals(accountsDto.getBranchAddress(), mappedEntity.getBranchAddress());
    }
}
