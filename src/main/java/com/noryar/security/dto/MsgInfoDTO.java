package com.noryar.security.dto;

import java.io.Serializable;

/**
 * 功能描述：License信息DTO.
 * @author Leon.
 *
 */
public class MsgInfoDTO implements Serializable{

	/**
	 * 序列化参数.
	 */
	private static final long serialVersionUID = 7204811275642070509L;

	/**
	 * 用户名.
	 */
	private String username;
	
	/**
	 * 证书开始时间.
	 */
	private String start;
	
	/**
	 * 证书结束时间.
	 */
	private String end;
	
	/**
	 * 证书存放路径.
	 */
	private String url;

	/**
	 * 功能描述：用户名的获取.
	 * @return 用户名.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 功能描述：用户名的设定.
	 * @param username 用户名.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 功能描述：证书开始时间的获取.
	 * @return 证书开始时间.
	 */
	public String getStart() {
		return start;
	}

	/**
	 * 功能描述：证书开始时间的设定.
	 * @param 证书开始时间.
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * 功能描述：证书结束时间的获取.
	 * @return 证书结束时间.
	 */
	public String getEnd() {
		return end;
	}

	/**
	 * 功能描述：证书结束时间的获取.
	 * @param 证书结束时间
	 */
	public void setEnd(String end) {
		this.end = end;
	}

	/**
	 * 功能描述：证书存放路径的获取.
	 * @return 证书存放路径.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 功能描述：证书存放路径的设定.
	 * @param url 证书存放路径.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
