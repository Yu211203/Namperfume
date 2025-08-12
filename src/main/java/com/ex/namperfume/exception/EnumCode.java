package com.ex.namperfume.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EnumCode {
    UNCATEGORIZE_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    PASSWORD_INVALID(1001, "Password must be greater than 8 characters", HttpStatus.BAD_REQUEST), USER_NOT_EXIST(1003, "Not found user in database", HttpStatus.NOT_FOUND),
    TRANSPORT_NOT_EXIST(1004, "Transport not exist in database",HttpStatus.NOT_FOUND),
    PAYMENT_NOT_EXIST(1005, "Payment method not found", HttpStatus.NOT_FOUND),
    ORDER_NOT_EXIST(1006, "Order not exist in database",HttpStatus.NOT_FOUND),
    PRODUCT_SIZE_NOT_EXIST(1007, "Product size not exist",HttpStatus.NOT_FOUND),
    ORDER_DETAIL_NOT_EXIST(1008, "Oder detail not exist",HttpStatus.NOT_FOUND),
    PRODUCT_NOT_EXIST(1009, "Product not exist in database",HttpStatus.NOT_FOUND),
    PRODUCT_TYPE_NOT_EXIST(1010, "Product type not exist",HttpStatus.NOT_FOUND),
    BRAND_NOT_EXIST(1011, "Brand not exist",HttpStatus.NOT_FOUND),
    SIZE_NOT_EXIST(1012, "Size not found", HttpStatus.NOT_FOUND),
    UNAUTHENTICATE(1013, "Unauthenticated", HttpStatus.UNAUTHORIZED)
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
    EnumCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
