package com.ex.namperfume.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailRequest {
    UUID order_id;
    UUID product_size_id;
    LocalDate create_date;
    long unit_price;
    long order_quantity;
    long total_price;
}
