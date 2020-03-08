package cn.com.hellowood.dynamicdatasource.config;

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
