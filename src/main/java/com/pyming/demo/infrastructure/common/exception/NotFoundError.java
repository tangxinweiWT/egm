package com.pyming.demo.infrastructure.common.exception;


import com.pyming.demo.infrastructure.common.http.HttpStatusEnum;

public class NotFoundError extends AppException {

    public NotFoundError() {
        super("数据不存在");
        this.code = HttpStatusEnum.GL404.code();
    }

    public NotFoundError(String message) {
        super(message);
        this.code = HttpStatusEnum.GL404.code();
    }
}
