package com.noryar.security.framework.db.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 功能描述：动态数据源路由.
 * @author Leon.
 *
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	static final Logger logger = LoggerFactory.getLogger(DataSourceKeyImpl.class);

	// 数据源key的存储控制器
	private DataSourceKey dataSourceKey;

	/**
	 * 获得数据源的key
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String key = "";
		try {
			key = dataSourceKey.getKey();
		} catch (Throwable e) {
			throw new RuntimeException("get data source key fail", e);
		}
		return key;
	}

	public DataSourceKey getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(DataSourceKey dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}
}
