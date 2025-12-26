package com.harshilInfotech.order_service.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderItemResponse(
        Long id,
        Long productId,
        Integer quantity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
