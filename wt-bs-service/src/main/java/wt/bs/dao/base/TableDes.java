package wt.bs.dao.base;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface TableDes {
    String nameSpace() default "";

    String tableName() default "";
}
