package com.ex.namperfume.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeResponse {
    UUID product_size_id;
    long initial_quantity;
    Long product_price;
    long stock_quantity;

    SizeResponse size;

}
