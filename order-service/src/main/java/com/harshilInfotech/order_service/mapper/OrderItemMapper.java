package com.harshilInfotech.order_service.mapper;

import com.harshilInfotech.order_service.dto.request.OrderItemRequest;
import com.harshilInfotech.order_service.dto.response.OrderItemResponse;
import com.harshilInfotech.order_service.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem toOrderItem(OrderItemRequest orderItemRequest) {
        return OrderItem.builder()
                .productId(orderItemRequest.productId())
                .quantity(orderItemRequest.quantity())
                .build();
    }

    public OrderItemResponse toOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .createdAt(orderItem.getCreatedAt())
                .updatedAt(orderItem.getUpdatedAt())
                .build();
    }

}
