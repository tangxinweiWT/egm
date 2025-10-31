package com.pyming.demo.infrastructure.vendors.jpaquery.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Join {
    // 连接的表名
    String name() default "";
    Way way() default Way.LEFT;
    // 主表的连接键
    String key() default "";
    // 副表的连接键
    String refKey() default "";
    // 连接方式
    enum Way {
        LEFT, RIGHT, INNER
    }
}
