package com.accounts.mapper;

import com.accounts.dto.CustomerDto;
import com.accounts.entity.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerMapperTest {

    @Test
    public void testMapToCustomerDto() {
        // Arrange
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customer.setMobileNumber("1234567890");

        CustomerDto customerDto = new CustomerDto();

        // Act
        CustomerDto mappedDto = CustomerMapper.mapToCustomerDto(customer, customerDto);

        // Assert
        assertNotNull(mappedDto);
        assertEquals(customer.getName(), mappedDto.getName());
        assertEquals(customer.getEmail(), mappedDto.getEmail());
        assertEquals(customer.getMobileNumber(), mappedDto.getMobileNumber());
    }

    @Test
    public void testMapToCustomer() {
        // Arrange
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Jane Doe");
        customerDto.setEmail("jane.doe@example.com");
        customerDto.setMobileNumber("0987654321");

        Customer customer = new Customer();

        // Act
        Customer mappedEntity = CustomerMapper.mapToCustomer(customerDto, customer);

        // Assert
        assertNotNull(mappedEntity);
        assertEquals(customerDto.getName(), mappedEntity.getName());
        assertEquals(customerDto.getEmail(), mappedEntity.getEmail());
        assertEquals(customerDto.getMobileNumber(), mappedEntity.getMobileNumber());
    }
}
