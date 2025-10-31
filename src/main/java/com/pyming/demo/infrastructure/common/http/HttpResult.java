package com.pyming.demo.infrastructure.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HttpResult {
    private int code;
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    // 失败的回复
    public static HttpResult error() {
        return error(HttpStatusEnum.GL500.code(), HttpStatusEnum.GL500.message());
    }

    public static HttpResult error(int code, String msg) {
        HttpResult r = new HttpResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    // 成功回复
    public static HttpResult ok(String msg) {
        HttpResult result = new HttpResult();
        result.setCode(HttpStatusEnum.GL200.code());
        result.setMsg(HttpStatusEnum.GL200.message());
        result.setData(msg);
        return result;
    }

    public static HttpResult ok(Object data) {
        HttpResult result = new HttpResult();
        result.setCode(HttpStatusEnum.GL200.code());
        result.setMsg(HttpStatusEnum.GL200.message());
        result.setData(data);
        return result;
    }

    public static HttpResult ok() {
        HttpResult result = new HttpResult();
        result.setCode(HttpStatusEnum.GL200.code());
        result.setMsg(HttpStatusEnum.GL200.message());
        return result;
    }
}
