package com.noryar.security.service.login.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noryar.security.constant.SecurityConstant;
import com.noryar.security.dao.QueryDAO;
import com.noryar.security.dao.UserDAO;
import com.noryar.security.dto.UserInfoDTO;
import com.noryar.security.framework.db.hbm.UserDTO;
import com.noryar.security.service.login.LoginService;
import com.noryar.security.util.DTOUtil;

/**
 * 功能描述：【用户登入】业务层.
 * @author Leon.
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	/**
	 * queryDAO.
	 */
	@Autowired
	private QueryDAO queryDAO;
	
	/**
	 * userDAO.
	 */
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserInfoDTO getUserByUnameAndPwd(UserInfoDTO searchModel) throws Exception {
		List<UserInfoDTO> userList = queryDAO.queryBySQLNamedQuery("getUserByUnameAndPwd", searchModel, UserInfoDTO.class);
		if (CollectionUtils.isEmpty(userList))
			return null;
		return userList.get(0);
	}

	@Override
	public void saveUser(UserInfoDTO userInfo) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(userInfo.getUsername());
		userDTO.setPassword(userInfo.getPassword());
		userDTO.setNickname(userInfo.getNickname());
		DTOUtil.initInsert(userDTO, SecurityConstant.ADMIN_USER_ID);
		userDAO.save(userDTO);
	}

}
