package com.swimtothend.base.api;

/**
 * create by BloodFly at 2019/1/21
 */
public class DefaultResponse<T> extends BaseResponse {
    private T data;

    public DefaultResponse() {
        super();
    }

    public DefaultResponse(T data) {
        super();
        setData(data);
    }

    public DefaultResponse(ResultCodeable result) {
        super(result);
    }

    public DefaultResponse(ResultCodeable result, T data) {
        super(result);
        setData(data);
    }

    public DefaultResponse(String resultCode, String resultMessage) {
        super(resultCode, resultMessage);
    }

    public DefaultResponse(String resultCode, String resultMessage, T data) {
        super(resultCode, resultMessage);
        setData(data);
    }

    public T getData() {
        return data;
    }


    public void setData(T data) {
        this.data = data;
    }
}
