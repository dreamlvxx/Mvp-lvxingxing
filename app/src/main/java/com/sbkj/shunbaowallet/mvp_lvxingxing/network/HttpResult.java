package com.sbkj.shunbaowallet.mvp_lvxingxing.network;

/**
 * Created by lvxingxing on 2018/2/8.
 *
 * @author lvxingxing
 *
 * Entity基类
 */

public class HttpResult<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
