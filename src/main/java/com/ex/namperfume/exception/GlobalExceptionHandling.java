package com.ex.namperfume.exception;

import com.ex.namperfume.dto.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ex.namperfume.exception.EnumCode;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Void>> handlingRuntimeException(Exception exception){
        log.error("Unhandled exception: "+exception);
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(EnumCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message("Internal server error")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Void>> handlingAppException(AppException e){
        log.error("AppException: {}", e.getMessage(), e);
        EnumCode code = e.getEnumCode();
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(code.getCode())
                .message(code.getMessage())
                .build();

        return ResponseEntity.status(mapToStatus(code)).body(apiResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ApiResponse<Void>> handleNotFound(EntityNotFoundException e){
        ApiResponse<Void> resp = ApiResponse.<Void>builder()
                .code(EnumCode.NOT_FOUND.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse<Void>> handlingDataIntegrityViolation(DataIntegrityViolationException e){
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(EnumCode.DATA_INTEGRITY_VIOLATION.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<ApiResponse<Void>> handlingAccessDenied(AccessDeniedException e){
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(EnumCode.ACCESS_DENIED.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
    }

    private HttpStatus mapToStatus(EnumCode code){
        return switch (code){
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case ACCESS_DENIED -> HttpStatus.FORBIDDEN;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case DATA_INTEGRITY_VIOLATION -> HttpStatus.CONFLICT;
            default -> HttpStatus.BAD_REQUEST;
        };
    }



}
