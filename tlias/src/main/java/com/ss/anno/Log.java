package com.ss.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)     // 表示该注解只能用于方法
@Retention(RetentionPolicy.RUNTIME)     // 表示该注解在运行时有效
public @interface Log {
}
