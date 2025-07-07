package com.apirest.apirestConsumer.infraestructure.adapter;

import com.apirest.apirestConsumer.domain.model.Product;
import com.apirest.apirestConsumer.domain.ports.ExternalApiServicePort;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Repository
public class ExternalApiAdapter implements ExternalApiServicePort {

    private final WebClient webClient;

    public ExternalApiAdapter(WebClient webClient) {
        this.webClient = webClient;
    }


    @Override
    public List<Integer> relatedProductsIdsById(String productId) {
        return webClient.get()
                .uri("/products/{id}/similarids", productId)
                .retrieve()
                .bodyToFlux(Integer.class)
                .collectList()
                .block(); // Blocking for simplicity, consider using reactive patterns in production code
    }

    @Override
    public Product relatedProductById(String productId) {
        return webClient.get()
                .uri("/products/{id}", productId)
                .retrieve()
                .bodyToMono(Product.class)
                .block(); // Blocking for simplicity, consider using reactive patterns in production code
    }
}
