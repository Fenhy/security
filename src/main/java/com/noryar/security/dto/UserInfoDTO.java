package com.noryar.security.dto;

import java.io.Serializable;

/**
 * 功能描述：用户信息.
 * @author Leon.
 *
 */
public class UserInfoDTO implements Serializable{
	
	/**
	 * 序列化参数.
	 */
	private static final long serialVersionUID = 4173767281816989478L;

	/**
	 * 用户id
	 */
	private String userid;
	
	/**
	 * 用户名.
	 */
	private String username;
	
	/**
	 * 用户密码.
	 */
	private String password;
	
	/**
	 * 昵称.
	 */
	private String nickname;

	/**
	 * 功能描述：用户id的获取.
	 * @return 用户id.
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * 功能描述：用户id的设定.
	 * @param username 用户id.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

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
	 * 功能描述：用户密码的获取.
	 * @return 用户密码.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 功能描述：用户密码的设定.
	 * @param password 用户密码.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 功能描述：昵称的获取.
	 * @return 昵称.
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 功能描述：昵称的设定.
	 * @param nickname 昵称.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
