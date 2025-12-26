package com.harshilInfotech.inventory_service.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        Long id,
        String title,
        Double price,
        Integer stock,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
