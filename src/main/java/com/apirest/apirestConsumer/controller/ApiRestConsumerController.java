package com.apirest.apirestConsumer.controller;

import com.apirest.apirestConsumer.domain.model.Product;
import com.apirest.apirestConsumer.domain.ports.ProductServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ApiRestConsumerController {

    private final ProductServicePort productServicePort;

    public ApiRestConsumerController(final ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @GetMapping("/{productId}/similar")
    public ResponseEntity<List<Product>> getSimilarProducts(String productId) {
        List<Product> products = productServicePort.getProductsByIds(productId);
        return ResponseEntity.ok(products);
    }
}
