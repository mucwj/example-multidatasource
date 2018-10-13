package com.github.mucwj.example.multidatasource.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 利用AOP来动态切换数据源
 */
@Aspect
// 保证该AOP在@Transactional之前执行
@Order(-10)
@Component
public class DynamicDataSourceAspect {

    @Before("@within(SwitchDataSource) || @annotation(SwitchDataSource)")
    public void changeDataSource(JoinPoint point) {
        // 获取方法上的注解
        Method method = ((MethodSignature)point.getSignature()).getMethod();
        SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);

        DSLabel value;
        if (Objects.isNull(annotation)) {
            // 方法上没有注解, 获取类上的注解
            annotation = point.getTarget().getClass().getAnnotation(SwitchDataSource.class);
            if (Objects.isNull(annotation)) {
                return;
            }
        }

        // 获取注解值
        value = annotation.value();
        // 切换数据源
        DynamicDataSourceContext.setDataSourceLabel(value);
    }

    @After("@within(SwitchDataSource) || @annotation(SwitchDataSource)")
    public void clean() {
        // 清理数据源的标签
        DynamicDataSourceContext.clearDataSourceLabel();
    }

}
