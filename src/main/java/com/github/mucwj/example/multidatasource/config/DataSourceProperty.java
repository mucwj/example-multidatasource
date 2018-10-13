package com.github.mucwj.example.multidatasource.config;

import lombok.Data;

/**
 * 数据源配置
 */
@Data
public class DataSourceProperty {

    /**
     * 数据源标签名称
     */
    private String label;

    /**
     * jdbc url
     */
    private String jdbcUrl;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 数据库密码
     */
    private String password;

}
