package com.noryar.security.service.login;

import com.noryar.security.dto.UserInfoDTO;

/**
 * 功能描述：【用户登入】业务层接口.
 * @author Leon.
 *
 */
public interface LoginService {

	/**
	 * 功能描述：通过用户名和密码查询用户.
	 * @param searchModel 查询对象.
	 * @return userInfoDTO.
	 * @throws Exception e.
	 */
	UserInfoDTO getUserByUnameAndPwd(UserInfoDTO searchModel) throws Exception;

	/**
	 * 功能描述：注册.
	 * @param userInfo 用户信息.
	 */
	void saveUser(UserInfoDTO userInfo);
	
}
