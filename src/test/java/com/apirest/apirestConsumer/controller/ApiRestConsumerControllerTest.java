package com.apirest.apirestConsumer.controller;

import com.apirest.apirestConsumer.domain.model.Product;
import com.apirest.apirestConsumer.domain.ports.ProductServicePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ApiRestConsumerControllerTest {

    private final ProductServicePort productServicePort = Mockito.mock(ProductServicePort.class);
    private final ApiRestConsumerController controller = new ApiRestConsumerController(productServicePort);

    @Nested
    @DisplayName("getSimilarProducts")
    class GetSimilarProducts {

        @Test
        @DisplayName("returns list of products when productId is valid")
        void returnsListOfProductsWhenProductIdIsValid() {
            String productId = "123";
            List<Product> mockProducts = Arrays.asList(
                    new Product("1", "Product 1", 10.00, true),
                    new Product("2", "Product 2", 20.00, true));
            when(productServicePort.getProductsByIds(productId)).thenReturn(mockProducts);

            ResponseEntity<List<Product>> response = controller.getSimilarProducts(productId);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(mockProducts, response.getBody());
        }

        @Test
        @DisplayName("returns empty list when no similar products are found")
        void returnsEmptyListWhenNoSimilarProductsAreFound() {
            String productId = "123";
            when(productServicePort.getProductsByIds(productId)).thenReturn(Collections.emptyList());

            ResponseEntity<List<Product>> response = controller.getSimilarProducts(productId);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(Collections.emptyList(), response.getBody());
        }

        @Test
        @DisplayName("throws exception when productId is null")
        void throwsExceptionWhenProductIdIsNull() {
            String productId = null;

            try {
                controller.getSimilarProducts(productId);
            } catch (IllegalArgumentException ex) {
                assertEquals("Product ID must not be null", ex.getMessage());
            }
        }
    }
}