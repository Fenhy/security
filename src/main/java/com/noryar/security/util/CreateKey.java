/*=============================================================
 * Copyright (c) 2009-2017 noryar.
 * All rights reserved.
 * 
 * 作者：          Leon.
 * 创建日期：  2017-3-28
 * 版本：          V1.0
 *============================================================*/
package com.noryar.security.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：【主键类】.
 * @author Leon.
 * 
 */
public class CreateKey {

    /** 应用程序级共享KEY值. */
    private static final Map<String, String> keyMap = new HashMap<String, String>();
    /** 主键时间戳 */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS"); 

    /**
     * 主键处理.
     * 
     * @param type 主键类型.
     * @return 主键的KEY值(23位).
     */
    public static synchronized String getKey(EnumCreateKey type) {
    	
        String key = sdf.format(new Date());
        if (keyMap.containsKey(type.toString())) {
            String value = keyMap.get(type.toString());
            if (value.indexOf(key + ",") == 0) {
                value = key + "," + String.format("%03d", Integer.parseInt(value.split(",")[1]) + 1);
            } else {
                value = key + "," + String.format("%03d", 1);
            }
            keyMap.put(type.toString(), value);
        } else {
            keyMap.put(type.toString(), key + "," + String.format("%03d", 1));
        }
        String prefix = "";
        if (type.equals(EnumCreateKey.LICENSE)) {
            // license认证
            prefix = "LIC";
        } else {
        	// 其他
        	prefix = "OTH";
        }
        return prefix + keyMap.get(type.toString()).replace(",", "");
    }

    /**
     * 功能描述：主键枚举.
     */
    public enum EnumCreateKey {
        LICENSE;
    }
}
