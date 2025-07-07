package com.apirest.apirestConsumer.domain.ports;

import com.apirest.apirestConsumer.domain.model.Product;

import java.util.List;

public interface ExternalApiServicePort {
    List<Integer> relatedProductsIdsById(String productId);
    Product relatedProductById(String productId);
}
