package com.harshilInfotech.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Builder
@Validated
public record OrderRequest(

        List<OrderItemRequest> orderItems,

        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price cannot be negative")
        Double totalPrice

) {
}
