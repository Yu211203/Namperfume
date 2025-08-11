package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.OrderDetailRequest;
import com.ex.namperfume.dto.response.OrderDetailResponse;
import com.ex.namperfume.entity.Order;
import com.ex.namperfume.entity.OrderDetail;
import com.ex.namperfume.entity.ProductSize;
import com.ex.namperfume.exception.AppException;
import com.ex.namperfume.exception.EnumCode;
import com.ex.namperfume.mapper.OrderDetailMapper;
import com.ex.namperfume.repository.OrderDetailRepository;
import com.ex.namperfume.repository.OrderRepository;
import com.ex.namperfume.repository.ProductSizeRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Transactional
public class OrderDetailService {
    OrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper;
    OrderRepository orderRepository;
    ProductSizeRepository productSizeRepository;

    public OrderDetailResponse createOrderDetail(OrderDetailRequest request) {
        Order order = orderRepository.findById(request.getOrder_id()).orElseThrow(()-> new AppException(EnumCode.ORDER_NOT_EXIST));

        ProductSize productSize = productSizeRepository.findById(request.getProduct_size_id()).orElseThrow(()-> new AppException(EnumCode.PRODUCT_SIZE_NOT_EXIST));

        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(request);
        orderDetail.setOrder(order);
        orderDetail.setProductSize(productSize);

        try
        {
            orderDetail = orderDetailRepository.save(orderDetail);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return orderDetailMapper.toOrderDetailResponse(orderDetail);

    }

    public OrderDetailResponse getOrderDetail(UUID order_detail_id){
        OrderDetail orderDetail =orderDetailRepository.findById(order_detail_id).orElseThrow(()-> new RuntimeException("Not found order detail with id: "+ order_detail_id));
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }

    public List<OrderDetailResponse> getOrderDetails(){
        return orderDetailRepository.findAll().stream().map(orderDetailMapper::toOrderDetailResponse).toList();
    }

    public List<OrderDetailResponse> getOrderDetailsByOrder(UUID order_id){

        Order order = orderRepository.findById(order_id).orElseThrow(()-> new AppException(EnumCode.ORDER_NOT_EXIST));
        return orderDetailRepository.findByOrder(order).stream().map(orderDetailMapper::toOrderDetailResponse).collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrderDetail(UUID order_detail_id){
        if(!orderDetailRepository.existsById(order_detail_id)){
            throw new AppException(EnumCode.ORDER_DETAIL_NOT_EXIST);
        }
        orderDetailRepository.deleteById(order_detail_id);
    }
}
