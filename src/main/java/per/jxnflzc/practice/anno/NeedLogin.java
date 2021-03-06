package per.jxnflzc.practice.anno;

import per.jxnflzc.practice.model.enums.PermissionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLogin {
    boolean required() default true;

    PermissionType permission() default PermissionType.OTHERS;
}
