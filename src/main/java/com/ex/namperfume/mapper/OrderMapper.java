package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.OrderRequest;
import com.ex.namperfume.dto.response.OrderResponse;
import com.ex.namperfume.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "order_id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "transport", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(target = "orderDetails", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    Order toOrder (OrderRequest request);

    @Mapping(target = "user_id", expression = "java(order.getUser()!=null ? order.getUser().getUser_id() :null)")
    @Mapping(target = "transport_id", expression = "java(order.getTransport()!= null ? order.getTransport().getTransport_id() :  null)")
    @Mapping(target = "payment_id", expression = "java(order.getPayment()!= null ? order.getPayment().getPayment_id() : null)")
    OrderResponse toOrderResponse(Order order);
}
