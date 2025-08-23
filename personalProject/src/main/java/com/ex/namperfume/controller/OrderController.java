package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.OrderRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.OrderResponse;
import com.ex.namperfume.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderController {

    OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest request){
        ApiResponse<OrderResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(orderService.createOrder(request));
        return apiResponse;
    }

    @GetMapping("/{order_id}")
    public ApiResponse<OrderResponse> getOrder(@PathVariable("order_id")UUID order_id){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrder(order_id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getOrders(){
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getOrders())
                .build();
    }

    @DeleteMapping("/{order_id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable("order_id") UUID order_id){
        orderService.deleteOrder(order_id);
        return ResponseEntity.noContent().build();
    }
}
