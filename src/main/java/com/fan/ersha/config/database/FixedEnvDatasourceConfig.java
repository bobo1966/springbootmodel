package com.fan.ersha.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *遥测分析
 * Created by jcb on 2017/6/28.
 */
@Configuration
@MapperScan(basePackages = FixedEnvDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "FixedEnvSqlSessionFactory")
public class FixedEnvDatasourceConfig {

    // 精确到数据库对应的dao 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.fan.ersha.dao.fixedEnv";
    // 精确到数据库对应的mapper目录
    static final String MAPPER_LOCATION = "classpath:mapper/Connection2/*.xml";

    @Value("${second.datasource.url}")
    private String url;

    @Value("${second.datasource.username}")
    private String user;

    @Value("${second.datasource.password}")
    private String password;

    @Value("${second.datasource.driverClassName}")
    private String driverClass;

    //数据源
    @Bean(name = "FixedEnvDatasource")
    public DataSource FixedEnvDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    //事务管理
    @Bean(name = "FixedEnvTransactionManager")
    public DataSourceTransactionManager FixedEnvTransactionManager() {
        return new DataSourceTransactionManager(FixedEnvDataSource());
    }

    //session工厂
    @Bean(name = "FixedEnvSqlSessionFactory")
    public SqlSessionFactory FixedEnvSqlSessionFactory(@Qualifier("FixedEnvDatasource") DataSource FixedEnvDatasource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(FixedEnvDatasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(FixedEnvDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
