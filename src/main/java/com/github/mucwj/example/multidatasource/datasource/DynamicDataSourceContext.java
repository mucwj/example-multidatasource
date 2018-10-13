package com.github.mucwj.example.multidatasource.datasource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 动态切换数据源的上下文, 用来修改和获取当前线程所使用的数据源标签
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DynamicDataSourceContext {

    private static final ThreadLocal<DSLabel> contextHolder = new ThreadLocal<>();

    public static void setDataSourceLabel(DSLabel label) {
        contextHolder.set(label);
    }

    public static DSLabel getDataSourceLabel() {
        return contextHolder.get();
    }

    public static void clearDataSourceLabel() {
        contextHolder.remove();
    }

}
