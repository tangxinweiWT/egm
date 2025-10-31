package com.pyming.demo.infrastructure.common.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = OneOfValidator.class)
public @interface OneOf {
    String message() default "参数值不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // 参数
    Class enumType() default Enum.class;
}

/**
 * 自定义验证类
 */
class OneOfValidator implements ConstraintValidator<OneOf, String> {
    private Class enumType = Enum.class;

    @Override
    public void initialize(OneOf constraintAnnotation) {
        Class enumType = constraintAnnotation.enumType();
        if (!enumType.isEnum()) {
            throw new RuntimeException("enumType 必须是枚举类型");
        }

        this.enumType = enumType;
    }

    @Override
    public boolean isValid(String o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (o == null) {
                return true;
            }
            Enum.valueOf(enumType, o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}