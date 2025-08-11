package com.ex.namperfume.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeRequest {
    UUID size_id;
    long initial_quantity;

    @NotNull(message = "Product price is required")
    @Min(value = 0, message = "Product price must be non-negative")
    Long product_price;

    long stock_quantity;
}
