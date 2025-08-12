package com.ex.namperfume.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    UUID order_detail_id;
    UUID order_id;
    UUID product_size_id;
    LocalDate create_date;
    long unit_price;
    long order_quantity;
    long total_price;
    LocalDateTime created_at;
}
