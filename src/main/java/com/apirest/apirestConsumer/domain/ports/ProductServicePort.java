package com.apirest.apirestConsumer.domain.ports;

import com.apirest.apirestConsumer.domain.model.Product;

import java.util.List;

public interface ProductServicePort {
    List<Product> getProductsByIds(String productId);
}
