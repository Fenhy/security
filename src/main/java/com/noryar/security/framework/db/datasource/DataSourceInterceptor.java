package com.noryar.security.framework.db.datasource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.PatternMatchUtils;

/**
 * 功能描述：数据源拦截器，基于方法的拦截.
 * @author Leon.
 *
 */
public class DataSourceInterceptor implements MethodInterceptor {
	
	// 方法和使用数据源key的对应关
	private Map<String, String> attributeSource = new HashMap<String, String>();
	// 数据源key的存储控制器
	private DataSourceKey dataSourceKey;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String methodName = invocation.getMethod().getName();
		String bestNameMatch = null;
		for (Iterator<String> it = this.attributeSource.keySet().iterator(); it
				.hasNext();) {
			String mappedName = it.next();
			if (isMatch(methodName, mappedName)
					&& (bestNameMatch == null || bestNameMatch.length() <= mappedName
							.length())) {
				bestNameMatch = mappedName;
			}
		}
		String key = attributeSource.get(bestNameMatch);
		if ("READ".equalsIgnoreCase(key)) {
			dataSourceKey.setReadKey();
		} else if ("WRITE".equalsIgnoreCase(key)) {
			dataSourceKey.setWriteKey();
		} else {
			dataSourceKey.setKey(key);
		}
		return invocation.proceed();
	}

	public void setAttributes(Map<String, String> attributeSource) {
		this.attributeSource = attributeSource;
	}

	private boolean isMatch(String methodName, String mappedName) {
		return PatternMatchUtils.simpleMatch(mappedName, methodName);
	}

	public DataSourceKey getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(DataSourceKey dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

}
