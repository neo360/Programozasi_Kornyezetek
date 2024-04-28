package com.accounts.dto;

import com.accounts.utils.ValidatorUtil;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerDtoTest {

    private final Validator validator = ValidatorUtil.getValidator();

    @Test
    void testValidCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Ajtai Alex");
        customerDto.setEmail("ajtaialex0922@gmail.com");
        customerDto.setMobileNumber("9345432123");

        Set<ConstraintViolation<CustomerDto>> violations = validator.validate(customerDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidCustomerDto() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("Eazy");
        customerDto.setEmail("invalid-email");
        customerDto.setMobileNumber("12345");

        Set<ConstraintViolation<CustomerDto>> violations = validator.validate(customerDto);
        assertEquals(0, violations.size());
    }
}

