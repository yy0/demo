package com.example.demo.config;

import lombok.Data;

/**
 * Created by wujianjiang on 2019-1-29.
 */
@Data
public class ServiceResult<T> {

    private String code;

    private T data;

    private String msg;

    public ServiceResult() {
    }

    public ServiceResult(String code) {
        this(code, null, null);
    }

    public ServiceResult(String code, String msg) {
        this(code, msg, null);
    }

    public ServiceResult(String code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
