package com.noryar.security.service.license.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.noryar.security.dao.MakeLicDAO;
import com.noryar.security.dao.QueryDAO;
import com.noryar.security.dto.MsgInfoDTO;
import com.noryar.security.dto.UserInfoDTO;
import com.noryar.security.framework.db.hbm.MakeLicDTO;
import com.noryar.security.framework.model.LicenseSearchModel;
import com.noryar.security.framework.model.PageInfoModel;
import com.noryar.security.service.license.LicenseService;
import com.noryar.security.util.DTOUtil;
import com.noryar.security.util.NoryarFileUtil;
import com.noryar.security.util.license.LicensePOI;
import com.noryar.security.util.license.LicenseUtil;

/**
 * 功能描述：【证书】业务层接口.
 * @author Leon.
 *
 */
@Service
public class LicenseServiceImpl implements LicenseService{
	
	/**
	 * makeLicDAO接口.
	 */
	@Autowired
	private MakeLicDAO makeLicDAO;
	
	/**
	 * queryDAO.
	 */
	@Autowired
	private QueryDAO queryDAO;
	
	@Override
	public byte[] getFile(String path) throws IOException {
		return NoryarFileUtil.getFile(path);
	}

	@Override
	public Map<String, Object> licenseVerify() throws Exception {
		Map<String,Object> result = new HashMap<String, Object>(1);
		LicenseUtil.LicenseVerify();
		result.put("msg", "verify success");
		return result;
	}

	@Override
	public Map<String, Object> licenseMake() throws Exception {
		Map<String,Object> result = new HashMap<String, Object>();
		LicenseUtil.LicenseMake();
		result.put("msg", "make success");
		return result;
	}

	@Override
	public List<File> createLicFile(byte[] msgbyteArr, UserInfoDTO loginUser) throws Exception {
		List<MsgInfoDTO> msgInfoList = LicensePOI.excelToDTO(msgbyteArr);
		List<File> licFileList = licenseMake(msgInfoList, loginUser.getUserid());
		return licFileList;
	}

	/**
	 * 功能描述：生产证书.
	 * @param msgInfoList 用户信息.
	 * @throws Exception e.
	 */
	private List<File> licenseMake(List<MsgInfoDTO> msgInfoList, String userId) throws Exception {
		List<File> licFileList = null;
		if (!CollectionUtils.isEmpty(msgInfoList))
			licFileList = new LinkedList<File>();
			for (MsgInfoDTO msgInfo : msgInfoList) {
				File file = LicenseUtil.LicenseMake(msgInfo);
				licFileList.add(file);
				// 成功生成一个，将信息插入db
				MakeLicDTO licDTO = new MakeLicDTO();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				licDTO.setStart(dateFormat.parse(msgInfo.getStart()));
				licDTO.setEnd(dateFormat.parse(msgInfo.getEnd()));
				licDTO.setUsername(msgInfo.getUsername());
				licDTO.setUrl(file.getPath().replace("\\", "/"));
				DTOUtil.initInsert(licDTO, userId);
				makeLicDAO.save(licDTO);
			}
		return licFileList;
	}

	@Override
	public int checkLicFile(File licFile, String username) throws Exception {
		return LicenseUtil.LicenseVerify(licFile, username);
	}

	@Override
	public PageInfoModel getLicMakeInfo(LicenseSearchModel searchModel) throws Exception {
		return queryDAO.pageQueryBySQLNamedQuery("getLicMakeInfoCnt", "getLicMakeInfo", searchModel, MakeLicDTO.class);
	}
	
}
