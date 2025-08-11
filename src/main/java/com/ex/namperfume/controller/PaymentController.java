package com.ex.namperfume.controller;

import com.ex.namperfume.dto.request.PaymentRequest;
import com.ex.namperfume.dto.response.ApiResponse;
import com.ex.namperfume.dto.response.PaymentResponse;
import com.ex.namperfume.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PaymentController {

    PaymentService paymentService;

    @PostMapping
    public ApiResponse<PaymentResponse> createPayment(@RequestBody PaymentRequest request){
        ApiResponse<PaymentResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(paymentService.createPayment(request));
        return apiResponse;
    }

    @GetMapping
    public ApiResponse<List<PaymentResponse>> getPayments(){
        return ApiResponse.<List<PaymentResponse>>builder()
                .result(paymentService.getPayments())
                .build();
    }

    @GetMapping("/{payment_id}")
    public ApiResponse<PaymentResponse> getPayment(@PathVariable("payment_id")UUID payment_id){
        return ApiResponse.<PaymentResponse>builder()
                .result(paymentService.getPayment(payment_id))
                .build();
    }

    @DeleteMapping("/{payment_id}")
    public String deletePayment(@PathVariable("payment_id")UUID payment_id){
        paymentService.detelePayment(payment_id);
        return "Payment method deleted successfully";
    }
}
