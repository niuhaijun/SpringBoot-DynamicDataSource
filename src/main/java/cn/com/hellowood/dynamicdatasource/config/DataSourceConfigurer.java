package cn.com.hellowood.dynamicdatasource.config;

import cn.com.hellowood.dynamicdatasource.common.DataSourceKey;
import com.github.pagehelper.PageInterceptor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Multiple DataSource Configurer
 *
 * @author HelloWood
 * @date 2017 -08-15 11:37
 * @Email hellowoodes @gmail.com
 */
@Configuration
public class DataSourceConfigurer {

	/**
	 * master DataSource
	 *
	 * @return data source
	 */
	@Primary
	@Bean("master")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.master")
	public DataSource master() {

		return DataSourceBuilder.create().build();
	}

	/**
	 * Slave alpha data source.
	 *
	 * @return the data source
	 */
	@Bean("slaveAlpha")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.slave-alpha")
	public DataSource slaveAlpha() {

		return DataSourceBuilder.create().build();
	}

	/**
	 * Slave beta data source.
	 *
	 * @return the data source
	 */
	@Bean("slaveBeta")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.slave-beta")
	public DataSource slaveBeta() {

		return DataSourceBuilder.create().build();
	}

	/**
	 * Slave gamma data source.
	 *
	 * @return the data source
	 */
	@Bean("slaveGamma")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.slave-gamma")
	public DataSource slaveGamma() {

		return DataSourceBuilder.create().build();
	}

	/**
	 * Dynamic data source.
	 *
	 * @return the data source
	 */
	@Bean("dynamicDataSource")
	public DataSource dynamicDataSource() {

		DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
		Map<Object, Object> dataSourceMap = new HashMap<>(4);
		dataSourceMap.put(DataSourceKey.master.name(), master());
		dataSourceMap.put(DataSourceKey.slaveAlpha.name(), slaveAlpha());
		dataSourceMap.put(DataSourceKey.slaveBeta.name(), slaveBeta());
		dataSourceMap.put(DataSourceKey.slaveGamma.name(), slaveGamma());

		// Set master datasource as default
		dynamicRoutingDataSource.setDefaultTargetDataSource(master());
		// Set master and slave datasource as target datasource
		dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);

		// To put datasource keys into DataSourceContextHolder to judge if the datasource is exist
		DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());

		// To put slave datasource keys into DataSourceContextHolder to load balance
		DynamicDataSourceContextHolder.slaveDataSourceKeys.addAll(dataSourceMap.keySet());
		DynamicDataSourceContextHolder.slaveDataSourceKeys.remove(DataSourceKey.master.name());
		return dynamicRoutingDataSource;
	}

	/**
	 * Transaction manager platform transaction manager.
	 *
	 * @return the platform transaction manager
	 */
	@Bean
	public PlatformTransactionManager transactionManager() {

		return new DataSourceTransactionManager(dynamicDataSource());
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {

		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource());
		bean.setTypeAliasesPackage("cn.com.hellowood.dynamicdatasource.mapper");

		//分页插件
		Properties properties = new Properties();
		properties.setProperty("reasonable", "false");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "count=countSql");

		//添加插件
		Interceptor interceptor = new PageInterceptor();
		interceptor.setProperties(properties);
		bean.setPlugins(new Interceptor[]{interceptor});

		//添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

		return bean;
	}
}

