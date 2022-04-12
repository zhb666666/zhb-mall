package com.zhb.mall.common;

import com.zhb.mall.exception.zhbMallExceptionEnum;

public class ApiRestResponse<T> {
    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    public ApiRestResponse() {
        this(OK_CODE, OK_MSG);
    }

    public static <T> ApiRestResponse<T> success() {
        return new ApiRestResponse<>();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ApiRestResponse<T> success(T result) {
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse<T> error(zhbMallExceptionEnum ex) {
        ApiRestResponse<T> response=new ApiRestResponse<>(ex.getCode(),ex.getMsg());
        return response;
    }

    public static <T> ApiRestResponse<T> error(Integer i,String s) {
        ApiRestResponse<T> response=new ApiRestResponse<>(i,s);
        return response;
    }


    private Integer status;
    private String msg;
    private T data;
    private static final int OK_CODE = 10000;
    private static final String OK_MSG = "SUCCESS";

}
