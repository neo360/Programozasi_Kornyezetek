package com.accounts.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResponseDtoTest {

    @Test
    void testResponseDto() {
        String statusCode = "200";
        String statusMsg = "Success";

        ResponseDto responseDto = new ResponseDto(statusCode, statusMsg);

        assertEquals(statusCode, responseDto.getStatusCode());
        assertEquals(statusMsg, responseDto.getStatusMsg());
    }

    @Test
    void testResponseDtoWithNullValues() {
        ResponseDto responseDto = new ResponseDto(null, null);

        assertNull(responseDto.getStatusCode());
        assertNull(responseDto.getStatusMsg());
    }
}
