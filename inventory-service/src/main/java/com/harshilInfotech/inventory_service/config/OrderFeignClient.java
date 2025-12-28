package com.harshilInfotech.inventory_service.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service", path = "/api/v1/orders")
public interface OrderFeignClient {

    @GetMapping("/hello-orders")
    ResponseEntity<String> helloOrdersController();

}
