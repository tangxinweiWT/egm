package com.pyming.demo.infrastructure.utils;


import com.pyming.demo.infrastructure.common.exception.NotFoundError;

public class ValidUtil {

    /**
     * 验证value是否为null，若为null，则throw NotFoundError
     */
    public static void ifNullThrow(Object value, String message) {
        if (value == null) {
            throw new NotFoundError(message);
        }
    }

    public static void ifNullThrow(Object value) {
        ValidUtil.ifNullThrow(value, "数据未找到");
    }
}
