package com.harshilInfotech.inventory_service.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record ProductRequest(

        @NotBlank(message = "Title cannot be empty")
        String title,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price cannot be negative")
        Double price,

        @NotNull(message = "Stock cannot be null")
        @Positive(message = "Stock cannot be negative")
        Integer stock
) {
}
