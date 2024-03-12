package com.zcy.rescue.passenger.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zlg
 * @Date: 2020/12/7 14:26
 * @Description:
 */
@Configuration
@MapperScan(basePackages = {"com.zcy.rescue.passenger.dao"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setDialectType(DbType.MYSQL.getDb());
    }

}
