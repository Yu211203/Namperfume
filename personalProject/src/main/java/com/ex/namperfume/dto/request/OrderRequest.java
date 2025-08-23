package com.ex.namperfume.dto.request;

import com.ex.namperfume.entity.OrderDetail;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    UUID user_id;
    UUID transport_id;
    UUID payment_id;
    String order_status;
    String payment_status;

    List<OrderDetailRequest> orderDetails;
}
