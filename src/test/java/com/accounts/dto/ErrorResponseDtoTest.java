package com.accounts.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ErrorResponseDtoTest {

    @Test
    void testErrorResponseDto() {
        String apiPath = "/api/accounts";
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        String errorMessage = "Account not found";
        LocalDateTime errorTime = LocalDateTime.now();

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(apiPath, errorCode, errorMessage, errorTime);

        assertEquals(apiPath, errorResponseDto.getApiPath());
        assertEquals(errorCode, errorResponseDto.getErrorCode());
        assertEquals(errorMessage, errorResponseDto.getErrorMessage());
        assertEquals(errorTime, errorResponseDto.getErrorTime());
    }

    @Test
    void testErrorResponseDtoWithNullValues() {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(null, null, null, null);

        assertNull(errorResponseDto.getApiPath());
        assertNull(errorResponseDto.getErrorCode());
        assertNull(errorResponseDto.getErrorMessage());
        assertNull(errorResponseDto.getErrorTime());
    }
}
