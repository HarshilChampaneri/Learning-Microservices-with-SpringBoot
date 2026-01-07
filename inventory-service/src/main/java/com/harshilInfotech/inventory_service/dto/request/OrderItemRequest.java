package com.harshilInfotech.inventory_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record OrderItemRequest(

        @NotNull(message = "ProductId cannot be Empty")
        Long productId,

        @NotNull(message = "Quantity cannot be Empty")
        @Positive(message = "Quantity must be positive.")
        Integer quantity

) {
}
