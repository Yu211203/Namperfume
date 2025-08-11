package com.ex.namperfume.exception;

public class AppException extends RuntimeException {
    public AppException(EnumCode enumCode) {
        super(enumCode.getMessage());
        this.enumCode = enumCode;
    }

    private EnumCode enumCode;
    public EnumCode getEnumCode(){
        return enumCode;
    }
    public void setEnumCode(EnumCode enumCode){
        this.enumCode = enumCode;
    }
}
