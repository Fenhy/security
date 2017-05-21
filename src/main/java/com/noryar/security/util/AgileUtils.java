package com.noryar.security.util;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.alibaba.fastjson.JSONObject;

/**
 * 其他工具类.
 * @author 蒋文武
 */
public class AgileUtils {
	
    /**
     * .
     */
    public static final byte C_0XF = 0xf;
    /**
     * .
     */
    public static final byte C_4 = 4;
    /**
     * 默认序列字段名.
     */
    private static final String C_SERIAL_VERSION_UID = "serialVersionUID";

    /**
     * MD5加密.
     * @param s s
     * @return String MD5加密结果
     */
    public static final String getMD5(final String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            // 使用MD5创建MessageDigest对象
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                str[k++] = hexDigits[b >> C_4 & C_0XF];
                str[k++] = hexDigits[b & C_0XF];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param queryString q
     * @return String 返回的查询个数语句.
     */
    public static final String getCountQueryString(final String queryString) {
        if (queryString.contains("with") || queryString.contains("WITH")) {
            String newQueryString = "select count(*) from (" + queryString + ")";
            return newQueryString;
        }
        int firstFromIndex = queryString.toLowerCase().indexOf("from");
        String newQueryString = "select count(*) " + queryString.substring(firstFromIndex);
        return newQueryString;
    }

    /**
     * 获得唯一标志.
     * @return String 唯一标志.
     */
    public static final String getKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 方法描述：根据模板字符串和对应的参数，获得合并后的字符串.
     * @param templateString 模板字符串.
     * @param paramsObj 参数对象.
     * @return String 返回合并计算后的字符串.
     * @throws Exception e.
     */
    public static String stringEvaluate(final String templateString, final Object paramsObj) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext();
        Field[] fields = paramsObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            String property = field.getName();
            if (C_SERIAL_VERSION_UID.equals(property)) {
                continue;
            }
            String propertyValue = BeanUtils.getProperty(paramsObj, property);
            if (!StringUtils.isEmpty(propertyValue)) {
                context.put(property, propertyValue);
            }
        }
        StringWriter writer = new StringWriter();
        ve.evaluate(context, writer, "", templateString);
        return writer.toString();
    }

    /**
     * 方法描述：根据模板字符串和对应的参数，获得合并后的字符串.
     * @param templateString 模板字符串.
     * @param paramsObj 参数对象.
     * @return String 返回合并计算后的字符串.
     * @throws Exception e.
     */
    public static String stringEvaluate(final String templateString, final JSONObject paramsObj) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext();
        Iterator<Entry<String, Object>> iterator = paramsObj.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entryInfo = iterator.next();
            String key = entryInfo.getKey();
            Object value = entryInfo.getValue();
            if (value != null && !value.equals("")) {
                context.put(key, value);
            }
        }
        StringWriter writer = new StringWriter();
        ve.evaluate(context, writer, "", templateString);
        return writer.toString();
    }
    
    /**
     * 方法描述：根据模板字符串和对应的参数，获得合并后的字符串.
     * @param templateString 模板字符串.
     * @param paramsObj 参数对象.
     * @return String 返回合并计算后的字符串.
     * @throws Exception e.
     */
    public static String stringEvaluate(final String templateString, final Map paramsObj) throws Exception {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        VelocityContext context = new VelocityContext();
        Iterator<Entry<String, Object>> iterator = paramsObj.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entryInfo = iterator.next();
            String key = entryInfo.getKey();
            Object value = entryInfo.getValue();
            if (value != null && !value.equals("")) {
                context.put(key, value);
            }
        }
        StringWriter writer = new StringWriter();
        ve.evaluate(context, writer, "", templateString);
        return writer.toString();
    }

    /**
     * 功能描述：判断字符串是否为数字类型.
     * @param str 待校验字符串.
     * @return 如果为数字返回true；否则返回false.
     */
    public static boolean isNumber(String str) {
    	if (StringUtils.isBlank(str))
    		return false;
    	return str.matches("^[0-9]*$");
    }
}
