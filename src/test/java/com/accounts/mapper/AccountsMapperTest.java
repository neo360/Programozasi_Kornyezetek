package com.accounts.mapper;

import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AccountsMapperTest {

    @Test
    public void testMapToAccountsDto() {
        // Arrange
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("Savings");
        accounts.setBranchAddress("123 Main Street, New York");

        AccountsDto accountsDto = new AccountsDto();

        // Act
        AccountsDto mappedDto = AccountsMapper.mapToAccountsDto(accounts, accountsDto);

        // Assert
        assertNotNull(mappedDto);
        assertEquals(accounts.getAccountNumber(), mappedDto.getAccountNumber());
        assertEquals(accounts.getAccountType(), mappedDto.getAccountType());
        assertEquals(accounts.getBranchAddress(), mappedDto.getBranchAddress());
    }

    @Test
    public void testMapToAccounts() {
        // Arrange
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType("Savings");
        accountsDto.setBranchAddress("123 Main Street, New York");

        Accounts accounts = new Accounts();

        // Act
        Accounts mappedEntity = AccountsMapper.mapToAccounts(accountsDto, accounts);

        // Assert
        assertNotNull(mappedEntity);
        assertEquals(accountsDto.getAccountNumber(), mappedEntity.getAccountNumber());
        assertEquals(accountsDto.getAccountType(), mappedEntity.getAccountType());
        assertEquals(accountsDto.getBranchAddress(), mappedEntity.getBranchAddress());
    }
}
