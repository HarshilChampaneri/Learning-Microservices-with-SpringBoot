package com.harshilInfotech.order_service.service.impl;

import com.harshilInfotech.order_service.dto.response.OrderResponse;
import com.harshilInfotech.order_service.entity.Order;
import com.harshilInfotech.order_service.mapper.OrderMapper;
import com.harshilInfotech.order_service.repository.OrderRepository;
import com.harshilInfotech.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponse> getAllOrders() {

        log.info("Fetching All Orders from database");
        List<Order> orders = orderRepository.findAll();
        log.info("Orders fetched successfully");

        log.info("Converting Orders to OrderResponse and Returning the list");
        return orders.stream()
                .map(orderMapper::toOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        log.info("Fetching Order with mentioned Id form the database");
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order with orderId: " + orderId + " not found"));
        log.info("Order fetched Successfully");

        log.info("Converting Order to OrderResponse and Returning the Order");
        return orderMapper.toOrderResponse(order);
    }

}
