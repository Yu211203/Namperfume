package com.ex.namperfume.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EnumCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    PASSWORD_INVALID(1001, "Password must be greater than 8 characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1003, "User not found in database", HttpStatus.NOT_FOUND),
    USER_EXIST(1004, "User already exists", HttpStatus.BAD_REQUEST),

    PRODUCT_NOT_EXIST(1005, "Product not found in database", HttpStatus.NOT_FOUND),
    PRODUCT_TYPE_NOT_EXIST(1006, "Product type not found", HttpStatus.NOT_FOUND),
    PRODUCT_SIZE_NOT_EXIST(1007, "Product size not found", HttpStatus.NOT_FOUND),

    ORDER_NOT_EXIST(1008, "Order not found", HttpStatus.NOT_FOUND),
    ORDER_DETAIL_NOT_EXIST(1009, "Order detail not found", HttpStatus.NOT_FOUND),

    BRAND_NOT_EXIST(1010, "Brand not found", HttpStatus.NOT_FOUND),
    SIZE_NOT_EXIST(1011, "Size not found", HttpStatus.NOT_FOUND),
    ROLE_NOT_EXIST(1012, "Role not found", HttpStatus.NOT_FOUND),

    PAYMENT_NOT_EXIST(1013, "Payment method not found", HttpStatus.NOT_FOUND),
    TRANSPORT_NOT_EXIST(1014, "Transport not found", HttpStatus.NOT_FOUND),

    TOKEN_INVALID(1015, "Token invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1016, "Unauthenticated", HttpStatus.UNAUTHORIZED), // 401
    ACCESS_DENIED(1017, "Access denied", HttpStatus.FORBIDDEN),        // 403
    DATA_INTEGRITY_VIOLATION(1018, "Data integrity violation", HttpStatus.CONFLICT),
    UNAUTHORIZED(1020, "Unauthorized", HttpStatus.UNAUTHORIZED),

    NOT_FOUND(1019, "Entity not found", HttpStatus.NOT_FOUND);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    EnumCode(int code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
