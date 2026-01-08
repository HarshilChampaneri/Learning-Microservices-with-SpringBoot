package com.harshilInfotech.order_service.service;

import com.harshilInfotech.order_service.dto.request.OrderRequest;
import com.harshilInfotech.order_service.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long orderId);

    OrderResponse createOrder(OrderRequest orderRequest);

}
