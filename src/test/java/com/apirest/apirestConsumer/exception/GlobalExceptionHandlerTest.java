package com.apirest.apirestConsumer.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();
    private final HttpServletRequest request = mock(HttpServletRequest.class);

    @Nested
    @DisplayName("handleHttpClientError")
    class HandleHttpClientError {

        @Test
        @DisplayName("returns internal server error response with message when exception has message")
        void returnsInternalServerErrorResponseWithMessageWhenExceptionHasMessage() {
            HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Client error");
            when(request.getRequestURI()).thenReturn("/test-uri");

            ResponseEntity<ErrorResponse> response = handler.handleHttpClientError(exception, request);

            assertEquals(500, response.getStatusCodeValue());
            assertEquals("/test-uri", response.getBody().getPath());
        }
    }

    @Nested
    @DisplayName("handleHttpServerError")
    class HandleHttpServerError {

        @Test
        @DisplayName("returns internal server error response with default message when exception has no message")
        void returnsInternalServerErrorResponseWithDefaultMessageWhenExceptionHasNoMessage() {
            HttpServerErrorException exception = new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            when(request.getRequestURI()).thenReturn("/test-uri");

            ResponseEntity<ErrorResponse> response = handler.handleHttpServerError(exception, request);

            assertEquals(500, response.getStatusCodeValue());
            assertEquals("/test-uri", response.getBody().getPath());
        }
    }

    @Nested
    @DisplayName("handleResourceAccess")
    class HandleResourceAccess {

        @Test
        @DisplayName("returns internal server error response with message when resource access exception occurs")
        void returnsInternalServerErrorResponseWithMessageWhenResourceAccessExceptionOccurs() {
            ResourceAccessException exception = new ResourceAccessException("Resource not accessible");
            when(request.getRequestURI()).thenReturn("/test-uri");

            ResponseEntity<ErrorResponse> response = handler.handleResourceAccess(exception, request);

            assertEquals(500, response.getStatusCodeValue());
            assertEquals("/test-uri", response.getBody().getPath());
        }
    }

    @Nested
    @DisplayName("handleValidation")
    class HandleValidation {

        @Test
        @DisplayName("returns bad request response with validation error message")
        void returnsBadRequestResponseWithValidationErrorMessage() {
            MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
            when(exception.getMessage()).thenReturn("Validation failed");
            when(request.getRequestURI()).thenReturn("/test-uri");

            ResponseEntity<ErrorResponse> response = handler.handleValidation(exception, request);

            assertEquals(400, response.getStatusCodeValue());
            assertEquals("/test-uri", response.getBody().getPath());
        }
    }

    @Nested
    @DisplayName("handleGeneral")
    class HandleGeneral {

        @Test
        @DisplayName("returns internal server error response with default message for general exception")
        void returnsInternalServerErrorResponseWithDefaultMessageForGeneralException() {
            Exception exception = new Exception();
            when(request.getRequestURI()).thenReturn("/test-uri");

            ResponseEntity<ErrorResponse> response = handler.handleGeneral(exception, request);

            assertEquals(500, response.getStatusCodeValue());
            assertEquals("/test-uri", response.getBody().getPath());
        }
    }
}