package com.noryar.security.controller.license;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.noryar.security.constant.SecurityConstant;
import com.noryar.security.dto.UserInfoDTO;
import com.noryar.security.framework.model.LicenseSearchModel;
import com.noryar.security.framework.model.PageInfoModel;
import com.noryar.security.service.license.LicenseService;
import com.noryar.security.util.NoryarFileUtil;

/**
 * 功能描述：【证书】控制层.
 * @author Leon.
 *
 */
@RestController
@RequestMapping("license")
public class LicenseController {
	
	/**
	 * 【证书】业务层接口.
	 */
	@Autowired
	private LicenseService licenseService;
	
	/**
	 * 功能描述：获取生成license文件模板.
	 * @return 模板文件.
	 * @throws IOException e.
	 */
	@RequestMapping("getTempFile.do")
	public ResponseEntity<byte[]> getTempFile() throws IOException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "licTemp.xlsx");
		return new ResponseEntity<byte[]>(licenseService.getFile("/licTempFile/licTemp.xlsx"), headers, HttpStatus.CREATED);
	}
	
	/**
	 * 功能描述：验证证书.
	 * @return 验证结果.
	 * @throws Exception e.
	 */
	@RequestMapping("verify.do")
	public Map<String, Object> licenseVerify() throws Exception{
		return licenseService.licenseVerify();
	}
	
	/**
	 * 功能描述：生成证书.
	 * @return 生成结果.
	 * @throws Exception e.
	 */
	@RequestMapping("make.do")
	public Map<String, Object> licenseMake() throws Exception{
		return licenseService.licenseMake();
	}
	
	/**
	 * 功能描述：上传用户信息并生产license.
	 * 
	 * @param session SESSION.
	 * @param msgfile 信息文件.
	 * @return
	 * @throws Exception e.
	 */
	@RequestMapping("createLicFile.do")
	public Map<String, Object> createLicFile(HttpSession session, MultipartFile msgfile) throws Exception{
		HashMap<String, Object> result = new HashMap<>();
		if (null == msgfile) {
			result.put("msg", "请上传文件");
		} else {
			String fileName = msgfile.getOriginalFilename();
			if (NoryarFileUtil.checkFileName(fileName, new String[]{"xls", "xlsx"})) {
				UserInfoDTO loginUser = (UserInfoDTO) session.getAttribute(SecurityConstant.SESSION_USER_INFO);
				byte[] msgbyteArr = msgfile.getBytes();
				List<File> licFileList = licenseService.createLicFile(msgbyteArr, loginUser);
				List<String> filePathList = new LinkedList<String>();
				for (File file : licFileList) {
					filePathList.add(file.getPath().replace("\\", "/"));
				}
				result.put("msg", "成功生成");
				result.put("files", filePathList);
			} else {
				result.put("msg", "请上传合法文件");
			}
		}
		return result;
	}
	
	/**
	 * 功能描述：获取license文件.
	 * 
	 * @param path 文件路径.
	 * @return lic文件.
	 * @throws IOException e.
	 */
	@RequestMapping("getLicFile.do")
	public ResponseEntity<byte[]> getLicFile(@RequestParam(required=true) String path) throws IOException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", "license.lic");
		return new ResponseEntity<byte[]>(NoryarFileUtil.getFile(new File(path)), headers, HttpStatus.CREATED);
	}
	
	/**
	 * 功能描述：上传用户lic文件并验证.
	 * 
	 * @param session SESSION.
	 * @param licfile 证书文件.
	 * @return
	 * @throws Exception e.
	 */
	@RequestMapping("checkLicFile.do")
	public Map<String, Object> checkLicFile(HttpSession session, MultipartFile licfile) throws Exception{
		HashMap<String, Object> result = new HashMap<>();
		if (null == licfile) {
			result.put("msg", "请上传文件");
		} else {
			String fileName = licfile.getOriginalFilename();
			if (NoryarFileUtil.checkFileName(fileName, new String[]{"lic"})) {
				String username = ((UserInfoDTO)session.getAttribute(SecurityConstant.SESSION_USER_INFO)).getUsername();
				String verfyFilePath = (File.separator + NoryarFileUtil.NORYAR_VFY_PATH + File.separator + username + File.separator + "license.lic").replace("\\", "/");
				File licFile = new File(verfyFilePath);
				if (!licFile.exists()) {
					licFile.getParentFile().mkdirs();
				}
				licfile.transferTo(licFile);
				int i = licenseService.checkLicFile(licFile, username);
				String resultMsg = "";
				switch (i) {
				case 0:
					resultMsg = "验证证书成功！";
					break;
				case 1:
					resultMsg = "证书已经过期！";
					break;
				case 2:
					resultMsg = "验证证书失败！";
					break;
				case 3:
					resultMsg = "证书与登入人不符合！";
					break;
				default:
					resultMsg = "证书安装失败！";
					break;
				}
				result.put("msg", resultMsg);
			} else {
				result.put("msg", "请上传合法文件");
			}
		}
		return result;
	}
	
	/**
	 * 功能描述：获取lic信息列表.
	 * @param searchModel 查询关键字.
	 * @return
	 * @throws Exception e.
	 */
	@RequestMapping("getLicMakeInfo.do")
	public Map<String, Object> getLicMakeInfo(LicenseSearchModel searchModel) throws Exception{
		Map<String, Object> result = new HashMap<>();
		PageInfoModel pageInfo = licenseService.getLicMakeInfo(searchModel);
		result.put("pageInfo", pageInfo);
		return result;
	}
	
}
