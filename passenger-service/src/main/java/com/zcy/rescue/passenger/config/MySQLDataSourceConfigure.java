package com.zcy.rescue.passenger.config;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Auther: zlg
 * @Date: 2020/11/23 13:59
 * @Description: 数据库连接配置
 */
@Configuration
@EnableTransactionManagement
public class MySQLDataSourceConfigure {

    private HikariProperties hikariProperties;

    @Autowired
    private PaginationInterceptor paginationInterceptor;

    public MySQLDataSourceConfigure(HikariProperties hikariProperties) {
        this.hikariProperties = hikariProperties;
    }

    @Bean
    public DataSource mysql() {
        DataSource dataSource = DataSourceBuilder.create().build();
        HikariDataSource hikariDataSource = null;
        if (dataSource instanceof HikariDataSource) {
            // 连接池配置
            System.out.println("连接池配置：" + hikariProperties.getUrl());
            hikariDataSource = (HikariDataSource) dataSource;
            hikariDataSource.setPoolName(hikariProperties.getPoolName());
            hikariDataSource.setAutoCommit(hikariProperties.isAutoCommit());
            hikariDataSource.setConnectionTestQuery(hikariProperties.getConnectionTestQuery());
            hikariDataSource.setIdleTimeout(hikariProperties.getIdleTimeout());
            hikariDataSource.setConnectionTimeout(hikariProperties.getConnectionTimeout());
            hikariDataSource.setMaximumPoolSize(hikariProperties.getMaximumPoolSize());
            hikariDataSource.setMaxLifetime(hikariProperties.getMaxLifetime());
            hikariDataSource.setMinimumIdle(hikariProperties.getMinimumIdle());
            hikariDataSource.setJdbcUrl(hikariProperties.getUrl());
            hikariDataSource.setUsername(hikariProperties.getUsername());
            hikariDataSource.setPassword(hikariProperties.getPassword());
            hikariDataSource.setDriverClassName(hikariProperties.getDriverClassName());
        }
        return hikariDataSource == null ? dataSource : hikariDataSource;
    }

    @Bean
    public SqlSessionFactory mysqlSqlSessionFactory(DataSource dataSource) throws Exception {
        //mybatis-plus配置
        // MyBatis-Plus使用MybatisSqlSessionFactoryBean  MyBatis直接使用SqlSessionFactoryBean
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();

        // 关键代码 设置 MyBatis-Plus 分页插件
        Interceptor[] plugins = {paginationInterceptor};
        bean.setPlugins(plugins);
        // 给MyBatis-Plus注入数据源
        bean.setDataSource(dataSource);
        //设置扫描xml包
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        //设置实体类别名所在包
        bean.setTypeAliasesPackage("com.zcy.rescue.passenger.entity");
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setLogImpl(StdOutImpl.class);
        mybatisConfiguration.setMapUnderscoreToCamelCase(true);
        mybatisConfiguration.setAutoMappingBehavior(AutoMappingBehavior.FULL);
        mybatisConfiguration.setCacheEnabled(true);
        mybatisConfiguration.setLazyLoadingEnabled(true);
        mybatisConfiguration.setMultipleResultSetsEnabled(true);
        bean.setConfiguration(mybatisConfiguration);
        GlobalConfig globalConfig = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setLogicDeleteValue("1");
        dbConfig.setLogicNotDeleteValue("0");
        //新增修改时验证null字段
        dbConfig.setFieldStrategy(FieldStrategy.DEFAULT);
        globalConfig.setDbConfig(dbConfig);
        bean.setGlobalConfig(globalConfig);
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager mysqlTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate mysqlSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
