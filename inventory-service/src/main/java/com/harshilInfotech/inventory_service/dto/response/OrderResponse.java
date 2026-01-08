package com.harshilInfotech.inventory_service.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(
        Long id,
        Double totalPrice,
        List<OrderItemResponse> orderItems,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
