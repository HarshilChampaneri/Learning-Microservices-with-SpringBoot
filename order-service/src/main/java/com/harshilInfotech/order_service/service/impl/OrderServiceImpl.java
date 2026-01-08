package com.harshilInfotech.order_service.service.impl;

import com.harshilInfotech.order_service.clients.InventoryFeignClient;
import com.harshilInfotech.order_service.dto.request.OrderRequest;
import com.harshilInfotech.order_service.dto.response.OrderResponse;
import com.harshilInfotech.order_service.entity.Order;
import com.harshilInfotech.order_service.entity.OrderItem;
import com.harshilInfotech.order_service.entity.enums.OrderStatus;
import com.harshilInfotech.order_service.mapper.OrderMapper;
import com.harshilInfotech.order_service.repository.OrderRepository;
import com.harshilInfotech.order_service.service.OrderService;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryFeignClient inventoryFeignClient;

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

    @Override
    @Transactional
    @Retry(name = "inventory-service-retry", fallbackMethod = "createOrderFallback")
    public OrderResponse createOrder(OrderRequest orderRequest) {

        log.info("Creating the Order");

        Double totalPrice = inventoryFeignClient.reduceStocks(orderRequest);

        Order order = orderMapper.toOrder(orderRequest);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus(OrderStatus.CONFIRMED);
        for (OrderItem orderItem : order.getOrderItems()) {
            orderItem.setOrder(order);
        }
        orderRepository.save(order);

        log.info("Created Order, Set Status to Order_Confirmed, Sending response.");

        return orderMapper.toOrderResponse(order);

    }

    @Override
    @Transactional
    public String cancelOrder(Long orderId) {

        log.info("Cancelling the Order");

        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new RuntimeException("Not Order found with orderId: " + orderId));

        if (order.getOrderStatus().equals(OrderStatus.CANCELLED)) {
            throw new RuntimeException("Order cannot be cancelled again");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        order = orderRepository.save(order);

        OrderResponse orderResponse = orderMapper.toOrderResponse(order);

        inventoryFeignClient.addStocks(orderResponse);

        return "Your Order with orderId: " + orderId + " has been cancelled successfully and the total price: " + orderResponse.totalPrice() + " has been refunded";

    }

    public OrderResponse createOrderFallback(OrderRequest orderRequest, Throwable throwable) {
        log.error("Fallback occurred due to : {}", throwable.getMessage());

        return OrderResponse.builder().build();
    }

}
