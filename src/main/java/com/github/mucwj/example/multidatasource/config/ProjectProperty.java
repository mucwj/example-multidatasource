package com.github.mucwj.example.multidatasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目配置类
 */
@Data
@ConfigurationProperties(prefix = "multi-data-source")
@Component
public class ProjectProperty {

    /**
     * 多个数据源配置
     */
    private List<DataSourceProperty> datasource;

}
