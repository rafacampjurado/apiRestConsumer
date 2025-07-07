package com.apirest.apirestConsumer.domain.service;

import com.apirest.apirestConsumer.domain.model.Product;
import com.apirest.apirestConsumer.domain.ports.ExternalApiServicePort;
import com.apirest.apirestConsumer.domain.ports.ProductServicePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductServicePort {

    private final ExternalApiServicePort externalApiServicePort;

    public ProductServiceImpl(ExternalApiServicePort externalApiServicePort) {
        this.externalApiServicePort = externalApiServicePort;
    }

    @Override
    public List<Product> getProductsByIds(String productId) {
        List<Integer> relatedProductIds = externalApiServicePort.relatedProductsIdsById(productId);
        return relatedProductIds.stream()
                .map(id -> externalApiServicePort.relatedProductById(String.valueOf(id)))
                .collect(Collectors.toList());
    }
}
