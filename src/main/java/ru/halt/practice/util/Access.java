package ru.halt.practice.util;

import java.lang.annotation.*;

/**
 * Created by Petr Rudenko on 06.02.2016.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Access {
    RoleUser[] roles() default {};
}
