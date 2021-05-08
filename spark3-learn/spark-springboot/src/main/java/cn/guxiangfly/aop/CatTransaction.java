package cn.guxiangfly.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author guxiang02
 * @Date 2020/8/20
 **/
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
public @interface CatTransaction {
    String type() default "";
    String name() default "";
    boolean isThrowOut() default true;
}
