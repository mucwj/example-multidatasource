package com.github.mucwj.example.multidatasource.config;

import com.github.mucwj.example.multidatasource.datasource.DSLabel;
import com.github.mucwj.example.multidatasource.datasource.RoutingDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    private DataSource createDataSource(DataSourceProperty property) {
        Properties properties = new Properties();
        properties.setProperty("jdbcUrl", property.getJdbcUrl());
        properties.setProperty("username", property.getUsername());
        properties.setProperty("password", property.getPassword());
        return new HikariDataSource(new HikariConfig(properties));
    }

    /**
     * 构造RoutingDataSource数据源
     * @param projectProperty 项目配置
     * @return  RoutingDataSource实例
     */
    @Bean
    public DataSource routingDataSource(ProjectProperty projectProperty) {
        Map<Object, Object> targetMap = new HashMap<>();
        for (DataSourceProperty dataSourceProperty : projectProperty.getDatasource()) {
            DataSource dataSource = createDataSource(dataSourceProperty);
            targetMap.put(DSLabel.valueOf(dataSourceProperty.getLabel()), dataSource);
        }

        RoutingDataSource routingDataSource = new RoutingDataSource();
        routingDataSource.setTargetDataSources(targetMap);
        // 设置默认使用的数据源
        routingDataSource.setDefaultTargetDataSource(targetMap.get(DSLabel.USER));
        return routingDataSource;
    }

}
