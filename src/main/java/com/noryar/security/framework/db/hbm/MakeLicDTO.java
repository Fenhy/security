package com.noryar.security.framework.db.hbm;

import java.util.Date;

/**
 * 功能描述：申请Lic文件DTO.
 * @author leon.
 *
 */
public class MakeLicDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300361420812010058L;
	private String licid;
	private Date start;
	private Date end;
	private String username;
	private String url;
	
	public String getLicid() {
		return licid;
	}
	public void setLicid(String licid) {
		this.licid = licid;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
