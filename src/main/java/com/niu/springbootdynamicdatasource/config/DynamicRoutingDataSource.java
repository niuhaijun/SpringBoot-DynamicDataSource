package com.niu.springbootdynamicdatasource.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Multiple DataSource Configurer
 *
 * @author HelloWood
 * @date 2017-08-15 11:37
 * @Email hellowoodes@gmail.com
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Set dynamic DataSource to Application Context
	 *
	 * 在事务环境中，以DynamicDataSourceContextHolder中的初始数据源为准，
	 * 事务执行后，才会aop切换数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {

		if (logger.isDebugEnabled()) {
			logger.debug("Current DataSource is [{}]",
				DynamicDataSourceContextHolder.getDataSourceKey());
		}

		return DynamicDataSourceContextHolder.getDataSourceKey();
	}
}
