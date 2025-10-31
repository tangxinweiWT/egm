package com.pyming.demo.infrastructure.common.exception;


import com.pyming.demo.infrastructure.common.http.HttpStatusEnum;

public class InvalidParamException extends AppException {
    public InvalidParamException(String message) {
        super(message);
        this.code = HttpStatusEnum.GL400.code();
    }

    public InvalidParamException() {
        super(HttpStatusEnum.GL400.message());
        this.code = HttpStatusEnum.GL400.code();
    }
}
