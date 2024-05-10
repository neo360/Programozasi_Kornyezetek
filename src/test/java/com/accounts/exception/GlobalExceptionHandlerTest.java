package com.accounts.exception;

import com.accounts.dto.ErrorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerTest {

    @Test
    void testErrorResponseDtoCreation() {
        LocalDateTime now = LocalDateTime.now();
        String apiPath = "/api/accounts";
        HttpStatus errorCode = HttpStatus.NOT_FOUND;
        String errorMessage = "Account not found";

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(apiPath, errorCode, errorMessage, now);

        assertEquals(apiPath, errorResponseDto.getApiPath());
        assertEquals(errorCode, errorResponseDto.getErrorCode());
        assertEquals(errorMessage, errorResponseDto.getErrorMessage());
        assertEquals(now, errorResponseDto.getErrorTime());
    }

    @Test
    void testHandleGlobalException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        MockHttpServletRequest request = new MockHttpServletRequest();

        ServletWebRequest servletWebRequest = new ServletWebRequest(request);

        Exception exception = new Exception("Internal Server Error");

        ResponseEntity<ErrorResponseDto> responseEntity = globalExceptionHandler.handleGlobalException(exception, servletWebRequest);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Internal Server Error", responseEntity.getBody().getErrorMessage());
    }

    @Test
    void testHandleResourceNotFoundException() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

        MockHttpServletRequest request = new MockHttpServletRequest();

        WebRequest webRequest = new ServletWebRequest(request);

        ResourceNotFoundException exception = new ResourceNotFoundException("Customer", "id", "123");

        ResponseEntity<ErrorResponseDto> responseEntity = globalExceptionHandler.handleResourceNotFoundException(exception, webRequest);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Customer not found with the given input data id : '123'", responseEntity.getBody().getErrorMessage());
    }
}
