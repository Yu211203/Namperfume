package com.ex.namperfume.service;

import com.ex.namperfume.dto.request.PaymentRequest;
import com.ex.namperfume.dto.response.PaymentResponse;
import com.ex.namperfume.entity.Payment;
import com.ex.namperfume.exception.AppException;
import com.ex.namperfume.exception.EnumCode;
import com.ex.namperfume.mapper.PaymentMapper;
import com.ex.namperfume.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;

    public PaymentResponse createPayment(PaymentRequest request){
        Payment payment = paymentMapper.toPayment(request);

        try{
            payment = paymentRepository.save(payment);

        }
        catch (AppException e) {
            throw new AppException(EnumCode.UNCATEGORIZE_EXCEPTION);
        }

        return paymentMapper.toPaymentResponse(payment);
    }

    public List<PaymentResponse> getPayments(){
        return paymentRepository.findAll().stream().map(paymentMapper::toPaymentResponse).toList();
    }

    public PaymentResponse getPayment(UUID payment_id){
        Payment payment = paymentRepository.findById(payment_id).orElseThrow(()-> new AppException(EnumCode.PAYMENT_NOT_EXIST));
        return paymentMapper.toPaymentResponse(payment);
    }

    public void detelePayment(UUID payment_id){
        paymentRepository.deleteById(payment_id);
    }


}
