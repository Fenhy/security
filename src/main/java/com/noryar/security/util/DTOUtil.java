package com.noryar.security.util;

import java.util.Date;

import com.noryar.security.framework.db.hbm.BaseDTO;

/**
 * 功能描述：DTO操作方法.
 * @author leon.
 *
 */
public class DTOUtil {

	/**
	 * 功能描述：初始化插入.
	 * @param dto dto对象.
	 * @param userid 操作人id.
	 */
	public static void initInsert(BaseDTO dto, String userid){
		Date date = new Date();
		dto.setCreateUser(userid);
		dto.setCreateDate(date);
		dto.setUpdateUser(userid);
		dto.setUpdateDate(date);
	}
	
	/**
	 * 功能描述：初始化更新.
	 * @param dto dto对象.
	 * @param userid 操作人id.
	 */
	public static void initUpdate(BaseDTO dto, String userid){
		Date date = new Date();
		dto.setUpdateUser(userid);
		dto.setUpdateDate(date);
	}
	
}
