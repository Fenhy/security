package com.noryar.security.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 功能描述：【工具】文件.
 * @author leon.
 *
 */
public class NoryarFileUtil {

	/**
	 * noryar系统lic文件根路径{@value}.
	 */
	public static final String NORYAR_LIC_PATH = "noryar-lic";
	
	/**
	 * noryar系统lic文件认证根路径{@value}.
	 */
	public static final String NORYAR_VFY_PATH = "noryar-vfy";
	
	/**
	 * 功能描述：获取文件.
	 * @param path 文件路径.
	 * @return 文件字节流.
	 * @throws IOException e.
	 */
	public static byte[] getFile(String path) throws IOException{
		return getFile(NoryarFileUtil.class.getResourceAsStream(path));
	}
	
	/**
	 * 功能描述：通过文件对象获取byte数组.
	 * @param file 文件对象.
	 * @return byte数组.
	 * @throws IOException e.
	 */
	public static byte[] getFile(File file) throws IOException{
		return getFile(new FileInputStream(file));
	}	
	
	/**
	 * 功能描述：将输入流转成byte数组.
	 * @param is 输入流.
	 * @return byte数组.
	 * @throws IOException e.
	 */
	public static byte[] getFile(InputStream is) throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int size = is.read(buffer);
		while(size != -1){
			baos.write(buffer, 0, size);
			size = is.read(buffer);
		}
		baos.flush();
		return baos.toByteArray();
	}
	
	/**
	 * 功能描述：判断文件是否符合扩展名.
	 * <pre>该方法并不会校验文件名是否为空。如若扩展名没有传递过来，则返回true</pre>
	 * @param fileName 文件名.
	 * @return boolean true:合法, false:不合法.
	 */
	public static boolean checkFileName(String fileName, String... exts) {
		boolean flag = false;
		if (exts == null || exts.length == 0){
			flag = true;
		} else {
			for (String ext : exts) {
				if (fileName.endsWith(ext)) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
}
