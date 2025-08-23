package com.ex.namperfume.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    UUID order_id;
    UUID user_id;
    UUID transport_id;
    UUID payment_id;
    String order_status;
    String payment_status;
    LocalDateTime created_at;

    List<OrderDetailResponse> orderDetails;
}
