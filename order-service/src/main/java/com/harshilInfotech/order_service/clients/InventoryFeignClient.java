package com.harshilInfotech.order_service.clients;

import com.harshilInfotech.order_service.dto.request.OrderRequest;
import com.harshilInfotech.order_service.dto.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory-service", path = "/api/v1/products")
public interface InventoryFeignClient {

    @PutMapping("/reduce-stocks")
    Double reduceStocks(OrderRequest orderRequest);

    @PutMapping("/add-stocks")
    Void addStocks(OrderResponse orderResponse);

}
