package com.noryar.security.service.license;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.noryar.security.dto.UserInfoDTO;
import com.noryar.security.framework.model.LicenseSearchModel;
import com.noryar.security.framework.model.PageInfoModel;

/**
 * 功能描述：【证书】业务层接口.
 * @author Leon.
 *
 */
public interface LicenseService {

	/**
	 * 功能描述：获取文件.
	 * @param path 文件路径.
	 * @return 文件.
	 * @throws IOException e.
	 */
	byte[] getFile(String path) throws IOException;

	/**
	 * 功能描述：验证证书.
	 * @return 验证结果.
	 * @throws Exception e.
	 */
	Map<String, Object> licenseVerify() throws Exception;

	/**
	 * 功能描述：生成证书.
	 * @return 生成结果.
	 * @throws Exception e.
	 */
	Map<String, Object> licenseMake() throws Exception;

	/**
	 * 功能描述：上传用户信息并生成license.
	 * @param msgfile 信息文件.
	 * @param loginUser 登入用户.
	 * @return 证书文件集合.
	 * @throws Exception e.
	 */
	List<File> createLicFile(byte[] msgbyteArr, UserInfoDTO loginUser) throws Exception;

	/**
	 * 功能描述：上传用户lic文件并验证.
	 * @param licFile 证书文件.
	 * @param username 证书拥有者.
	 * @return int 验证状态码，0：验证证书成功！，1：证书已经过期！，2：验证证书失败！，3：证书与登入人不符合！，4：安装证书失败！
	 * @throws Exception e.
	 */
	int checkLicFile(File licFile, String username) throws Exception;

	/**
	 * 功能描述：获取lic信息列表.
	 * @param searchModel 查询列表.
	 * @return 信息列表.
	 * @throws Exception e.
	 */
	PageInfoModel getLicMakeInfo(LicenseSearchModel searchModel) throws Exception;
	
}
