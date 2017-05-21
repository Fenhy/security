package com.noryar.security.util.license;

import java.io.File;

import com.noryar.security.dto.MsgInfoDTO;

/**
 * 功能描述：license工具类.
 * @author Leon.
 *
 */
public class LicenseUtil {

	/**
	 * 功能描述：证书生成.
	 * @throws Exception e.
	 */
	public static void LicenseMake() throws Exception {
		LicenseMaker clicense = new LicenseMaker("/licenseMakeConf.properties");
		clicense.create();
	}

	/**
	 * 功能描述：证书生成.
	 * @param msgInfo 用户信息.
	 */
	public static File LicenseMake(MsgInfoDTO msgInfo) throws Exception {
		LicenseMaker clicense = new LicenseMaker("/licenseMakeConf.properties", msgInfo);
		return clicense.create();
	}
	
	/**
	 * 功能描述：证书验证.
	 * @throws Exception e.
	 */
	public static void LicenseVerify() throws Exception {
		LicenseVerifier vlicense = new LicenseVerifier("noryar"); // 项目唯一识别码，对应生成配置文件的subject
		vlicense.install("/");
		vlicense.vertify();
	}
	
	/**
	 * 功能描述：证书验证.
	 * 
	 * @param licFile 证书文件.
	 * @param username 证书拥有者.
	 * @return int 验证状态码，0：验证证书成功！，1：证书已经过期！，2：验证证书失败！，3：证书与登入人不符合！，4：安装证书失败！
	 * @throws Exception e.
	 */
	public static int LicenseVerify(File licFile, String username) throws Exception {
		LicenseVerifier vlicense = new LicenseVerifier(username); // 这里对于证书文件的拥有者.
		try{
			vlicense.install(licFile);
		} catch (Exception e) {
			if (e.getMessage().indexOf("exc.invalidSubject") != -1){
				return 3;
			}
			return 4;
		}
		return vlicense.vertify();
	}
	
	public static void main(String[] args) throws Exception {
		LicenseMake();
	}
}
