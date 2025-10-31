package com.pyming.demo.infrastructure.common.exception;


import com.pyming.demo.infrastructure.common.http.HttpStatusEnum;

public class InvalidTokenException extends AppException{
    public InvalidTokenException(String message) {
        super(message);
        this.code = HttpStatusEnum.GL403.code();
    }
}
