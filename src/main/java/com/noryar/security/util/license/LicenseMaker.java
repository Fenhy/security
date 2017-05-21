package com.noryar.security.util.license;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.prefs.Preferences;

import javax.security.auth.x500.X500Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noryar.security.dto.MsgInfoDTO;
import com.noryar.security.util.NoryarFileUtil;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultKeyStoreParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;

/**
 * 功能描述：生成证书器.
 * @author Leon.
 */
public class LicenseMaker {
	private Logger log = LoggerFactory.getLogger(getClass());
	private String licPath;
	private String issued;
	private String notBefore;
	private String notAfter;
	private String consumerType;
	private int consumerAmount;
	private String info;
	private MsgInfoDTO msgInfo = null;
	/**
	 * 私钥的别名
	 */
	private String priAlias;
	/**
	 * 该密码生成密钥对的密码
	 */
	private String privateKeyPwd;
	/**
	 * 使用keytool生成密钥对时设置的密钥库的访问密码
	 */
	private String keyStorePwd;
	private String subject;
	private String priPath;

	// X500Princal是一个证书文件的固有格式，详见API
	private final static X500Principal DEFAULTHOLDERANDISSUER = new X500Principal(
			"CN=noryar, OU=noryar, O=noryar, L=china, ST=dalian, C=china");

	public LicenseMaker() {
	}

	public LicenseMaker(String confPath) {
		initParam(confPath);
	}
	
	public LicenseMaker(String confPath, MsgInfoDTO msgInfo){
		if (null != msgInfo)
			this.msgInfo = msgInfo;
		initParam(confPath);
	}

	/**
	 * 功能描述：读取属性文件.
	 * @param propertiesPath
	 *            属性文件路径
	 */
	public void initParam(String confPath) {
		// 获取参数
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream(confPath);
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// common parameters
		priAlias = prop.getProperty("private.key.alias");
		privateKeyPwd = prop.getProperty("private.key.pwd");
		keyStorePwd = prop.getProperty("key.store.pwd");
		//subject = prop.getProperty("subject");
		// subject 作为项目唯一识别号，也可用户用户名
		subject = msgInfo.getUsername();
		priPath = File.separator + prop.getProperty("priPath");		
		issued = prop.getProperty("issuedTime");
		// dynamic parameters
		if (null != msgInfo) {
			notAfter = msgInfo.getEnd();
			notBefore = msgInfo.getStart();
			licPath = msgInfo.getUsername() + File.separator + prop.getProperty("licPath");
		} else {
			notAfter = prop.getProperty("notAfter");
			notBefore = prop.getProperty("notBefore");
			licPath = prop.getProperty("licPath");
		}
		// format path
		priPath = priPath.replace("\\", "/");
		licPath = (File.separator + NoryarFileUtil.NORYAR_LIC_PATH + File.separator + licPath).replace("\\", "/");
		consumerType = prop.getProperty("consumerType");
		consumerAmount = Integer.valueOf(prop.getProperty("consumerAmount"));
		info = prop.getProperty("info");
	}

	/**
	 * 功能描述：初始化证书的相关参数.
	 * @return
	 */
	private LicenseParam initLicenseParams() {
		Class<LicenseMaker> clazz = LicenseMaker.class;
		Preferences pre = Preferences.userNodeForPackage(clazz);
		// 设置对证书内容加密的对称密码
		CipherParam cipherParam = new DefaultCipherParam(keyStorePwd);
		// 参数1,2从哪个Class.getResource()获得密钥库;
		// 参数3密钥库的别名;
		// 参数4密钥库存储密码;
		// 参数5密钥库密码
		KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(clazz, priPath, priAlias, keyStorePwd,
				privateKeyPwd);
		// 返回生成证书时需要的参数
		LicenseParam licenseParam = new DefaultLicenseParam(subject, pre, privateStoreParam, cipherParam);
		return licenseParam;
	}

	/**
	 * 功能描述：通过外部配置文件构建证书的的相关信息.
	 * @return
	 * @throws ParseException e.
	 */
	public LicenseContent buildLicenseContent() throws ParseException {
		LicenseContent content = new LicenseContent();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
		content.setConsumerAmount(consumerAmount);
		content.setConsumerType(consumerType);
		content.setHolder(DEFAULTHOLDERANDISSUER);
		content.setIssuer(DEFAULTHOLDERANDISSUER);
		content.setIssued(formate.parse(issued));
		content.setNotBefore(formate.parse(notBefore));
		content.setNotAfter(formate.parse(notAfter));
		content.setInfo(info);
		content.setExtra(new Object());
		return content;
	}

	/**
	 * 功能描述：生成证书，在证书发布者端执行.
	 * @throws Exception e.
	 */
	public File create() throws Exception {
		LicenseManager licenseManager = LicenseManagerHolder.getLicenseManager(initLicenseParams());
		LicenseContent content = buildLicenseContent();
		File licFile = new File(licPath);
		if (!licFile.exists()){
			licFile.getParentFile().mkdirs();
			licFile.createNewFile();
		}
		licenseManager.store(content, licFile);
		log.info(String.format("证书发布成功，存放位置【%s】", licFile.getAbsolutePath()));
		return licFile;
	}
}