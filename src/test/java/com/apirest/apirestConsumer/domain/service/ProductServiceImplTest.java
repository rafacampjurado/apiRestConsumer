package com.apirest.apirestConsumer.domain.service;

import com.apirest.apirestConsumer.domain.model.Product;
import com.apirest.apirestConsumer.domain.ports.ExternalApiServicePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    private final ExternalApiServicePort externalApiServicePort = Mockito.mock(ExternalApiServicePort.class);
    private final ProductServiceImpl productService = new ProductServiceImpl(externalApiServicePort);

    @Nested
    @DisplayName("getProductsByIds")
    class GetProductsByIds {

        @Test
        @DisplayName("returns list of products when related product IDs are found")
        void returnsListOfProductsWhenRelatedProductIdsAreFound() {
            String productId = "123";
            List<Integer> relatedIds = List.of(1, 2);
            List<Product> expectedProducts =  Arrays.asList(
                    new Product("1", "Product 1", 10.00, true),
                    new Product("2", "Product 2", 20.00, true));

            when(externalApiServicePort.relatedProductsIdsById(productId)).thenReturn(relatedIds);
            when(externalApiServicePort.relatedProductById("1")).thenReturn(expectedProducts.get(0));
            when(externalApiServicePort.relatedProductById("2")).thenReturn(expectedProducts.get(1));

            List<Product> result = productService.getProductsByIds(productId);

            assertEquals(expectedProducts, result);
        }

        @Test
        @DisplayName("returns empty list when no related product IDs are found")
        void returnsEmptyListWhenNoRelatedProductIdsAreFound() {
            String productId = "123";

            when(externalApiServicePort.relatedProductsIdsById(productId)).thenReturn(List.of());

            List<Product> result = productService.getProductsByIds(productId);

            assertEquals(List.of(), result);
        }

        @Test
        @DisplayName("throws exception when productId is null")
        void throwsExceptionWhenProductIdIsNull() {
            String productId = null;

            try {
                productService.getProductsByIds(productId);
            } catch (IllegalArgumentException ex) {
                assertEquals("Product ID must not be null", ex.getMessage());
            }
        }
    }
}