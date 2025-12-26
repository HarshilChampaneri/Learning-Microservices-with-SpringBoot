package com.harshilInfotech.order_service.dto.response;

import com.harshilInfotech.order_service.entity.enums.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        Double totalPrice,
        OrderStatus orderStatus,
        List<OrderItemResponse> orderItems,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
