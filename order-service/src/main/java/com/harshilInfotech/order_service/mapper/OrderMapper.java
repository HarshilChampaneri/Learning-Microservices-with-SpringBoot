package com.harshilInfotech.order_service.mapper;

import com.harshilInfotech.order_service.dto.request.OrderRequest;
import com.harshilInfotech.order_service.dto.response.OrderResponse;
import com.harshilInfotech.order_service.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .totalPrice(orderRequest.totalPrice())
                .orderItems(orderRequest.orderItems().stream()
                        .map(orderItemMapper::toOrderItem)
                        .toList()
                )
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .id(order.getId())
                .updatedAt(order.getUpdatedAt())
                .createdAt(order.getCreatedAt())
                .orderItems(order.getOrderItems().stream()
                        .map(orderItemMapper::toOrderItemResponse)
                        .toList()
                )
                .totalPrice(order.getTotalPrice())
                .build();
    }

}
