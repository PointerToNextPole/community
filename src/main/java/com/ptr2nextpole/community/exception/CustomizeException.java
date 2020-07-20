package com.ptr2nextpole.community.exception;

public class CustomizeException extends RuntimeException{

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
