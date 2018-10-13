package com.github.mucwj.example.multidatasource.datasource;

import java.lang.annotation.*;

/**
 * 切换数据源的注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwitchDataSource {

    /**
     * 切换到指定的数据库
     * @return 数据库标签
     */
    DSLabel value();

}
