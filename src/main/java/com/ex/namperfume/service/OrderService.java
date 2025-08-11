package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.OrderRequest;
import com.ex.namperfume.dto.response.OrderResponse;
import com.ex.namperfume.entity.Order;
import com.ex.namperfume.entity.Payment;
import com.ex.namperfume.entity.Transport;
import com.ex.namperfume.entity.User;
import com.ex.namperfume.exception.AppException;
import com.ex.namperfume.exception.EnumCode;
import com.ex.namperfume.mapper.OrderMapper;
import com.ex.namperfume.repository.OrderRepository;
import com.ex.namperfume.repository.PaymentRepository;
import com.ex.namperfume.repository.TransportRepository;
import com.ex.namperfume.repository.UserRepository;
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
public class OrderService {
    OrderMapper orderMapper;
    OrderRepository orderRepository;
    UserRepository userRepository;
    TransportRepository transportRepository;
    PaymentRepository paymentRepository;

    public OrderResponse createOrder(OrderRequest request){
        User user = userRepository.findById(request.getUser_id()).orElseThrow(()-> new AppException(EnumCode.USER_NOT_EXIST));
        Transport transport = transportRepository.findById(request.getTransport_id()).orElseThrow(()-> new AppException(EnumCode.TRANSPORT_NOT_EXIST));
        Payment payment = paymentRepository.findById(request.getPayment_id()).orElseThrow(()-> new AppException(EnumCode.PAYMENT_NOT_EXIST));

        Order order = orderMapper.toOrder(request);
        order.setUser(user);
        order.setTransport(transport);
        order.setPayment(payment);

        try{
            order = orderRepository.save(order);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return orderMapper.toOrderResponse(order);
    }

    public OrderResponse getOrder(UUID order_id){
        Order order = orderRepository.findById(order_id).orElseThrow(()-> new RuntimeException("Not found order with id: "+ order_id));
        return orderMapper.toOrderResponse(order);
    }

    public List<OrderResponse> getOrders(){
        return orderRepository.findAll().stream().map(orderMapper::toOrderResponse).toList();
    }

    @Transactional
    public List<OrderResponse> getOrdersByUser(UUID user_id){
        User user = userRepository.findById(user_id).orElseThrow(()-> new AppException(EnumCode.USER_NOT_EXIST));
        return orderRepository.findOrdersByUser(user).stream().map(orderMapper::toOrderResponse).collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse updateOrderStatus(UUID order_id, String newStatus){
        Order order = orderRepository.findById(order_id).orElseThrow(()-> new RuntimeException("not found order with id: "+order_id));
        order.setOrder_status(newStatus);
        order = orderRepository.save(order);

        return orderMapper.toOrderResponse(order);
    }

    @Transactional
    public void deleteOrder(UUID order_id){
        if(!orderRepository.existsById(order_id)){
            throw new RuntimeException("Not found order with id: "+ order_id);
        }
        orderRepository.deleteById(order_id);
    }
}
