package com.mskl.common.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = {LoginValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
    String message() default "用户名或者密码不正确!!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
