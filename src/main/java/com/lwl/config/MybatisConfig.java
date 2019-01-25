package com.lwl.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.lwl.dataSource.RouteDataSource;
import com.lwl.enums.RouteDataSourceKeyEnum;

/**
 * 配置mybatis数据源
 * @author lwl
 * @create 2019年1月23日 下午5:07:10
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = "com.lwl.mapper")
public class MybatisConfig {

	@Autowired
	private Environment env;

	/**
	 * 配置主库数据源
	 * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
	 */
	@Bean
	public DataSource masterDbDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("spring.datasource.master.driverClassName"));
		props.put("url", env.getProperty("spring.datasource.master.url"));
		props.put("username", env.getProperty("spring.datasource.master.username"));
		props.put("password", env.getProperty("spring.datasource.master.password"));
		return DruidDataSourceFactory.createDataSource(props);
	}

	/**
	 * 配置从库数据源
	 * @return
	 * @throws Exception
	 * @author lwl
	 * @create 2019年1月24日 下午1:10:08
	 */
	@Bean
	public DataSource slaveDataSource() throws Exception {
		Properties props = new Properties();
		props.put("driverClassName", env.getProperty("spring.datasource.slave.driverClassName"));
		props.put("url", env.getProperty("spring.datasource.slave.url"));
		props.put("username", env.getProperty("spring.datasource.slave.username"));
		props.put("password", env.getProperty("spring.datasource.slave.password"));
		return DruidDataSourceFactory.createDataSource(props);
	}

	/**
	 * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
	 * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
	 */
	@Bean
	@Primary
	public RouteDataSource dataSource(@Qualifier("masterDbDataSource") DataSource masterDbDataSource,
			@Qualifier("slaveDataSource") DataSource slaveDataSource) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(RouteDataSourceKeyEnum.MASTER.name(), masterDbDataSource);
		targetDataSources.put(RouteDataSourceKeyEnum.SLAVE.name(), slaveDataSource);

		RouteDataSource dataSource = new RouteDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法

		return dataSource;
	}

	/**
	 * 根据数据源创建SqlSessionFactory
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDbDataSource") DataSource masterDbDataSource,
			@Qualifier("slaveDataSource") DataSource slaveDataSource) throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();

		fb.setDataSource(this.dataSource(masterDbDataSource, slaveDataSource));
		// 以下2个是通过xml配置的，如果项目中的sql文件没有使用xml格式（xxxxMapper.xml），则不需要
		// fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));
		// fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));

		return fb.getObject();

	}

	/**
	 * 配置事务管理器
	 */
	@Bean
	public DataSourceTransactionManager transactionManager(RouteDataSource dataSource) throws Exception {
		return new DataSourceTransactionManager(dataSource);
	}

}
