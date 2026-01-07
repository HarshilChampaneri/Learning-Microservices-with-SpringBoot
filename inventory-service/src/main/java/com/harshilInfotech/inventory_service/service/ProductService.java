package com.harshilInfotech.inventory_service.service;

import com.harshilInfotech.inventory_service.dto.request.OrderRequest;
import com.harshilInfotech.inventory_service.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    Double reduceStocks(OrderRequest orderRequest);

}
