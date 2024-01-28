package com.zcy.rescue.passenger.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源
 * 2）创建SqlSessionFactory
 */
@Configuration    //该注解类似于spring配置文件
@MapperScan(basePackages = "com.zcy.rescue.passenger.dao")
public class MybatisConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    @Primary
    public DataSource openDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        datasource.setUrl(env.getProperty("spring.datasource.url"));
        datasource.setUsername(env.getProperty("spring.datasource.username"));
        datasource.setPassword(env.getProperty("spring.datasource.password"));
        datasource.setDefaultAutoCommit(false);
        datasource.setMaxActive(20);
        datasource.setInitialSize(10);
        datasource.setMaxWait(60000);
        datasource.setMinIdle(5);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setMinEvictableIdleTimeMillis(30000);
        datasource.setValidationQuery("select 'x'");
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(true);
        datasource.setTestOnReturn(true);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxOpenPreparedStatements(20);
        return datasource;
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        try {
            SqlSessionFactoryBean readSqlSessionFactory = new SqlSessionFactoryBean();
            readSqlSessionFactory.setDataSource(dataSource);
            readSqlSessionFactory.setTypeAliasesPackage("com.zcy.rescue.passenger.entity");
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            readSqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
            readSqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
            return readSqlSessionFactory.getObject();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean(name = "platformTransactionManager")
    @Primary
    public PlatformTransactionManager annotationDrivenTransactionManager(
            @Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
