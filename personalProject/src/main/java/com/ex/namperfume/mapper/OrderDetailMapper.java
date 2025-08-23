package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.OrderDetailRequest;
import com.ex.namperfume.dto.response.OrderDetailResponse;
import com.ex.namperfume.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(target = "order_detail_id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "productSize", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    OrderDetail toOrderDetail(OrderDetailRequest request);

    @Mapping(target = "order_id", expression = "java(orderDetail.getOrder()!=null ? orderDetail.getOrder().getOrder_id() : null)")
    @Mapping(target = "product_size_id",expression = "java(orderDetail.getProductSize()!= null ? orderDetail.getProductSize().getProduct_size_id() : null)")
    OrderDetailResponse toOrderDetailResponse (OrderDetail orderDetail);



}
