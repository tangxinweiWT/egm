package com.pyming.demo.infrastructure.utils;

import org.springframework.beans.BeanUtils;

public class BeanCopy {
    /**
     * 拷贝对象属性，忽略 id和createdAt
     * @param source
     * @param target
     */
    public static void copy(Object source, Object target) {
        BeanUtils.copyProperties(source, target, "id", "createdAt");
    }
}
