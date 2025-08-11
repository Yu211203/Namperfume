package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.OrderDetailRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.OrderDetailResponse;
import com.ex.namperfume.service.OrderDetailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/details")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PostMapping
    public ApiResponse<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest request){
        ApiResponse<OrderDetailResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(orderDetailService.createOrderDetail(request));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<OrderDetailResponse>> getOrderDetails(){
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetails())
                .build();
    }

    @GetMapping("/{order_detail_id}")
    public ApiResponse<OrderDetailResponse> getOrderDetail(@PathVariable("order_detail_id")UUID order_detail_id){
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.getOrderDetail(order_detail_id))
                .build();
    }

    @DeleteMapping("/{order_detail_id}")
    public String deleteOrderDetail(@PathVariable("order_detail_id") UUID order_detail_id){
        orderDetailService.deleteOrderDetail(order_detail_id);
        return "Order detail deleted successfully";
    }
}
