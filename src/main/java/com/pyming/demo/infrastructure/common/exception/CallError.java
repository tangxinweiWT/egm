package com.pyming.demo.infrastructure.common.exception;


import com.pyming.demo.infrastructure.common.http.HttpStatusEnum;

public class CallError extends AppException {
    public CallError(String message, int code) {
        super(message, code);
    }

    public CallError(String message) {
        super(message);
        this.code = HttpStatusEnum.GL1000.code();
    }
}
