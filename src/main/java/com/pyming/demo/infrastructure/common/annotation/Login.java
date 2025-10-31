package com.pyming.demo.infrastructure.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 身份认证注解。
 * 情况一：  @Login, 只判断用户是否登录。
 * 情况二： @Login(right="user:add") 判断用户是否拥有user:add权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    // 判断权限，如果没有则不需要
    String right() default "";
}
