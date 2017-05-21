package com.noryar.security.framework.db.hbm;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能描述：基础DTO类.
 * @author leon.
 *
 */
public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8238229855936091212L;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	
}
