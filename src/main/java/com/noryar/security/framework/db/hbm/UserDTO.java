package com.noryar.security.framework.db.hbm;

/**
 * 功能描述：用户信息DTO.
 * @author Leon.
 *
 */
public class UserDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4917773252565968821L;
	private String userid;
	private String username;
	private String nickname;
	private String password;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
