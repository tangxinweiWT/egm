package com.pyming.demo.infrastructure.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 应用程序基础异常类
 */
@Getter
@Setter
public class AppException extends RuntimeException {
    protected int code;

    public AppException(String message, Throwable cause) {
        super(message,cause);
    }

    public AppException(String message, int code) {
        super(message);
        this.code = code;
    }

    public AppException(String message) {
        super(message);
    }
}
