package com.harshilInfotech.order_service.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "inventory-service", path = "/api/v1/products")
public interface InventoryFeignClient {



}
