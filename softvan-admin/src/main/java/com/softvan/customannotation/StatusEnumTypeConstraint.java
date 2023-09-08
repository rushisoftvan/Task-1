package com.softvan.customannotation;

import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusEnumTypeConstraint {

    String message() default "Status type does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
