package com.ex.namperfume.mapper;

import com.ex.namperfume.dto.request.PaymentRequest;
import com.ex.namperfume.dto.response.PaymentResponse;
import com.ex.namperfume.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment toPayment(PaymentRequest request);
    PaymentResponse toPaymentResponse(Payment payment);
}
