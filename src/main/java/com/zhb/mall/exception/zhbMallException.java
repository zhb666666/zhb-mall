package com.zhb.mall.exception;

/**
 * 描述：     统一异常
 */
public class zhbMallException extends RuntimeException {

    private final Integer code;
    private final String message;

    public zhbMallException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public zhbMallException(zhbMallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
