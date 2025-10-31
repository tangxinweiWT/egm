package com.pyming.demo.infrastructure.common.http;

public enum HttpStatusEnum {
    /**
     * 成功
     */
    GL200(200, "成功"),
    GL500(500, "系统异常"),
    GL400(400, "请求参数错误"),
    GL401(401, "未授权"),
    GL403(403, "无权访问"),
    GL404(404, "资源不存在"),
    GL1000(1000, "第三方服务调用失败");

    private int code;
    private String message;

    HttpStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }
}
