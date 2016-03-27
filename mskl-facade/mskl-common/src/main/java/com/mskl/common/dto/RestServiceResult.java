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
    //服务描述
    private String desc;

    public RestServiceResult() {
    }

    public RestServiceResult(String desc, boolean isSuccess) {
        this.desc = desc;
        this.isSuccess = isSuccess;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
        return isSuccess()? desc+"执行成功!" : desc+"失败,原因是:"+message;
    }
}
