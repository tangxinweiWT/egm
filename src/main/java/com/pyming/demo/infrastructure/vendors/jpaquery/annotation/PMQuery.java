package com.pyming.demo.infrastructure.vendors.jpaquery.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JPA 查询辅助注解，用于自动生成Spec
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PMQuery {
    // 基本对象的属性名
    String propName() default "";

    // 查询方式
    Type type() default Type.EQ;

    // 模糊查询时的字段名
    String[] blurry() default {};

    // 时间查询时的字段名
    String timeAttr() default "";

    enum Type {
        // 相等
        EQ,
        // 不相等
        NE,
        // 大于
        GT,
        // 小于
        LT,
        // 大于等于
        GE,
        // 小于等于
        LE,
        // IN操作
        IN,
        // NOT IN 操作
        NOT_IN,
        IS_NULL,
        NOT_NULL,
        BETWEEN,
        // 模糊查询
        LIKE,
        NOT_LIKE,
        BEGIN_WITH,
        END_WITH,
        // 时间大于等于
        TIME_GE,
        // 时间小于等于
        TIME_LE
    }
}
