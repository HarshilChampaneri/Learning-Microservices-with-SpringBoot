package com.harshilInfotech.inventory_service.mapper;

import com.harshilInfotech.inventory_service.dto.request.ProductRequest;
import com.harshilInfotech.inventory_service.dto.response.ProductResponse;
import com.harshilInfotech.inventory_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .title(productRequest.title())
                .stock(productRequest.stock())
                .price(productRequest.price())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .stock(product.getStock())
                .title(product.getTitle())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

}
