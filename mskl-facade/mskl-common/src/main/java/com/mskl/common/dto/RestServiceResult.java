package com.mskl.common.dto;

import java.io.Serializable;

public final class RestServiceResult<T> implements Serializable {
    //是否成功
    private boolean isSuccess;
    //信息
    private String message;
    //错误码
    private String code;
    //返回对象
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return isSuccess()? "服务执行成功!" : "服务失败,原因是:"+message;
    }
}
