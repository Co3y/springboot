package com.example.common;

import java.lang.annotation.*;

/**
 * @Author: ZengFK
 * @Date: 2024/3/25 21:44
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {
    String value() default "";
}
