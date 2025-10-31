package com.pyming.demo.infrastructure.common.exception;


import com.pyming.demo.infrastructure.common.http.HttpStatusEnum;

public class AuthError extends AppException {
    public AuthError(String message) {
        super(message);
        this.code = HttpStatusEnum.GL401.code();
    }
}