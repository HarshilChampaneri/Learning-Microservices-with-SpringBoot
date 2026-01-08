package com.harshilInfotech.inventory_service.controller;

import com.harshilInfotech.inventory_service.config.OrderFeignClient;
import com.harshilInfotech.inventory_service.dto.request.OrderRequest;
import com.harshilInfotech.inventory_service.dto.response.OrderResponse;
import com.harshilInfotech.inventory_service.dto.response.ProductResponse;
import com.harshilInfotech.inventory_service.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final OrderFeignClient orderFeignClient;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/fetch-orders")
    public ResponseEntity<String> fetchOrdersFromOrderService(HttpServletRequest request) {

        log.info(request.getHeader("X-Custom-Header"));

        return orderFeignClient.helloOrdersController();

    }

    @PutMapping("/reduce-stocks")
    public ResponseEntity<Double> reduceStocks(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(productService.reduceStocks(orderRequest));
    }

    @PutMapping("/add-stocks")
    public Void addStocks(@RequestBody OrderResponse orderResponse) {
        return productService.addStocks(orderResponse);
    }

}
