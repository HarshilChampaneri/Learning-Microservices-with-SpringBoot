package com.harshilInfotech.inventory_service.service.impl;

import com.harshilInfotech.inventory_service.dto.response.ProductResponse;
import com.harshilInfotech.inventory_service.entity.Product;
import com.harshilInfotech.inventory_service.mapper.ProductMapper;
import com.harshilInfotech.inventory_service.repository.ProductRepository;
import com.harshilInfotech.inventory_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProducts() {

        log.info("Fetching All Products from database");
        List<Product> products = productRepository.findAll();
        log.info("Products fetched successfully");

        log.info("Converting Products to ProductResponse and Returning the list");
        return products.stream()
                .map(productMapper::toProductResponse)
                .toList();

    }

    @Override
    public ProductResponse getProductById(Long productId) {

        log.info("Fetching Product with mentioned Id form the database");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No Product found by Id: " + productId));
        log.info("Product fetched Successfully");

        log.info("Converting Product to ProductResponse and Returning the Product");
        return productMapper.toProductResponse(product);

    }

}
