package com.ex.namperfume.exception;

import com.ex.namperfume.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        log.error("Exception: "+exception);
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(EnumCode.UNCATEGORIZE_EXCEPTION.getCode());
        apiResponse.setMessage(EnumCode.UNCATEGORIZE_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e){
        log.error("Exception: "+e);
        EnumCode enumCode = e.getEnumCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(enumCode.getCode());
        apiResponse.setMessage(enumCode.getMessage());

        return ResponseEntity.ok().body(apiResponse);
    }





}
