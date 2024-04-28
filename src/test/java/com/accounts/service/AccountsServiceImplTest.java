package com.accounts.service;

import com.accounts.constants.AccountsConstants;
import com.accounts.dto.CustomerDto;
import com.accounts.exception.ResourceNotFoundException;
import com.accounts.mapper.CustomerMapper;
import com.accounts.repository.AccountsRepository;
import com.accounts.repository.CustomerRepository;
import com.accounts.service.impl.AccountsServiceImpl;
import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;
import com.accounts.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountsServiceImplTest {

    @Mock
    private AccountsRepository accountsRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountsServiceImpl accountsService;

    private Customer customer;

    private Accounts accounts;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customer = new Customer();
        customer.setCustomerId(1L);
        accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
    }

    @Test
    void testCreateAccount_Success() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("John Doe");
        customerDto.setEmail("john.doe@example.com");
        customerDto.setMobileNumber("1234567890");

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        when(customerRepository.findByMobileNumber(customerDto.getMobileNumber())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setAccountNumber(1234567890L);
        accountsDto.setAccountType(AccountsConstants.SAVINGS);
        accountsDto.setBranchAddress(AccountsConstants.ADDRESS);

        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setAccountNumber(accountsDto.getAccountNumber());
        newAccount.setAccountType(accountsDto.getAccountType());
        newAccount.setBranchAddress(accountsDto.getBranchAddress());

        when(accountsRepository.save(any(Accounts.class))).thenReturn(newAccount);

        accountsService.createAccount(customerDto);

        verify(customerRepository, times(1)).findByMobileNumber(customerDto.getMobileNumber());
        verify(customerRepository, times(1)).save(any(Customer.class));
        verify(accountsRepository, times(1)).save(any(Accounts.class));
    }

    @Test
    void testFetchAccount_Success() {
        String mobileNumber = "1234567890";
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setMobileNumber(mobileNumber);

        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1234567890L);
        accounts.setAccountType("Savings");
        accounts.setBranchAddress("123 Main St");
        accounts.setCustomerId(customer.getCustomerId());

        when(customerRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(customer));
        when(accountsRepository.findByCustomerId(customer.getCustomerId())).thenReturn(Optional.of(accounts));

        CustomerDto result = accountsService.fetchAccount(mobileNumber);

        assertNotNull(result);
        assertEquals(customer.getName(), result.getName());
        assertEquals(customer.getEmail(), result.getEmail());
        assertEquals(customer.getMobileNumber(), result.getMobileNumber());

        AccountsDto accountsDto = result.getAccountsDto();
        assertNotNull(accountsDto);
        assertEquals(accounts.getAccountNumber(), accountsDto.getAccountNumber());
        assertEquals(accounts.getAccountType(), accountsDto.getAccountType());
        assertEquals(accounts.getBranchAddress(), accountsDto.getBranchAddress());
    }

    @Test
    void testFetchAccount_CustomerNotFound() {
        String mobileNumber = "1234567890";

        when(customerRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> accountsService.fetchAccount(mobileNumber)
        );
        assertEquals("Customer not found with the given input data mobileNumber : '1234567890'", exception.getMessage());
    }

    @Test
    void testFetchAccount_AccountNotFound() {
        String mobileNumber = "1234567890";
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setMobileNumber(mobileNumber);

        when(customerRepository.findByMobileNumber(mobileNumber)).thenReturn(Optional.of(customer));
        when(accountsRepository.findByCustomerId(customer.getCustomerId())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = org.junit.jupiter.api.Assertions.assertThrows(
                ResourceNotFoundException.class,
                () -> accountsService.fetchAccount(mobileNumber)
        );
        assertEquals("Account not found with the given input data customerId : '1'", exception.getMessage());
    }

    @Test
    void testDeleteAccount_Success() {
        when(customerRepository.findByMobileNumber(any())).thenReturn(Optional.of(customer));

        boolean result = accountsService.deleteAccount("1234567890");

        assertTrue(result);
        verify(accountsRepository, times(1)).deleteByCustomerId(customer.getCustomerId());
        verify(customerRepository, times(1)).deleteById(customer.getCustomerId());
    }

    @Test
    void testDeleteAccount_CustomerNotFound() {
        when(customerRepository.findByMobileNumber(any())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountsService.deleteAccount("1234567890"));
        verify(accountsRepository, never()).deleteByCustomerId(any());
        verify(customerRepository, never()).deleteById(any());
    }
}
