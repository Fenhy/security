package com.noryar.security.framework.model;

import java.io.Serializable;

/**
 * 功能描述：license模块查询模型.
 * @author leon.
 *
 */
public class LicenseSearchModel extends PageInfoModel implements Serializable {

	/**
	 * 序列化参数.
	 */
	private static final long serialVersionUID = -1151358741865637703L;
	
	/**
	 * 查询关键字.
	 */
	private String keyWords;

	/**
	 * 功能描述：查询关键字的获取.
	 * @return 查询关键字.
	 */
	public String getKeyWords() {
		return keyWords;
	}

	/**
	 * 功能描述：查询关键字的设定.
	 * @param keyWords 查询关键字.
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	
	
}
