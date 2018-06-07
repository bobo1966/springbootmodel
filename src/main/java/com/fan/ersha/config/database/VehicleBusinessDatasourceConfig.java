package com.fan.ersha.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 遥测数据库
 * Created by jcb on 2017/6/28.
 */
@Configuration
@MapperScan(basePackages = VehicleBusinessDatasourceConfig.PACKAGE, sqlSessionFactoryRef = "VehicleBusinessSqlSessionFactory")
public class VehicleBusinessDatasourceConfig {
    // 精确到 数据库对应的dao 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.fan.ersha.dao.vehicleBusiness";
    //精确到数据库对应的mapper目录
    static final String MAPPER_LOCATION = "classpath:mapper/Connection3/*.xml";

    @Value("${primary.datasource.url}")
    private String url;

    @Value("${primary.datasource.username}")
    private String user;

    @Value("${primary.datasource.password}")
    private String password;

    @Value("${primary.datasource.driverClassName}")
    private String driverClass;

    //数据源
    @Bean(name = "VehicleBusinessDatasource")
    //必须有这个注解，要不要会没有默认的数据库
    @Primary
    public DataSource VehicleBusinessDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    //事务管理
    @Bean(name = "VehicleBusinessTransactionManager")
    @Primary
    public DataSourceTransactionManager VehicleBusinessTransactionManager() {
        return new DataSourceTransactionManager(VehicleBusinessDataSource());
    }

    //session工厂
    @Bean(name = "VehicleBusinessSqlSessionFactory")
    @Primary
    public SqlSessionFactory VehicleBusinessSqlSessionFactory(@Qualifier("VehicleBusinessDatasource") DataSource VehicleBusinessDatasource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(VehicleBusinessDatasource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(VehicleBusinessDatasourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
