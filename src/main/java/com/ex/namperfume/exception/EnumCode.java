package com.ex.namperfume.exception;

import lombok.Getter;

@Getter
public enum EnumCode {
    UNCATEGORIZE_EXCEPTION(9999, "Uncategorized error"),
    PASSWORD_INVALID(1001, "Password must be greater than 8 characters"),
    USER_EXISTED(1002, "User existed"),
    USER_NOT_EXIST(1003, "Not found user in database"),
    TRANSPORT_NOT_EXIST(1004, "Transport not exist in database"),
    PAYMENT_NOT_EXIST(1005, "Payment method not found"),
    ORDER_NOT_EXIST(1006, "Order not exist in database"),
    PRODUCT_SIZE_NOT_EXIST(1007, "Product size not exist"),
    ORDER_DETAIL_NOT_EXIST(1008, "Oder detail not exist")
    ;

    int code;
    String message;
    EnumCode(int code, String message){
        this.code = code;
        this.message = message;
    }
}
