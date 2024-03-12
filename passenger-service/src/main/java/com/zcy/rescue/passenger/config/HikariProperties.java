package com.zcy.rescue.passenger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zouhx
 * @Date: 2024/11/23 13:54
 * @Description: 数据库Hikari连接配置属性
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.mysql.hikari")
public class HikariProperties {

    private String poolName;

    private boolean autoCommit;

    private long connectionTimeout;

    private long idleTimeout;

    private long maxLifetime;

    private int maximumPoolSize;

    private int minimumIdle;

    private String connectionTestQuery;

    private String driverClassName;

    private String url;

    private String username;

    private String password;
}
