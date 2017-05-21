package com.noryar.security.util;

/**
 * 功能描述：驼峰命名转换.
 * @author Leon.
 */
public final class CamelCaseUtils {
    /**
     * 默认构造函数.
     */
    private CamelCaseUtils() {
    }

    /**
     * 分隔字符.
     */
    private static final char SEPARATOR = '_';

    /**
     * 功能说明：驼峰名称转换成下划线名称.
     * <pre>返回的下划线名称为小写</pre>
     * @param s 传入的驼峰名称.
     * @return String 返回下划线命名模式的名称.
     */
    public static String toUnderlineName(final String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean nextUpperCase = true;
            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }
            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0) {
                        sb.append(SEPARATOR);
                    }
                }
                upperCase = true;
            } else {
                upperCase = false;
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 功能说明：下划线名称转换成驼峰名称.
     * <pre>下划线名称将先转换成小写</pre>
     * @param underlineName 传入的下划线名称.
     * @return String 返回驼峰名称.
     */
    public static String toCamelCase(final String underlineName) {
        if (underlineName == null) {
            return null;
        }
        String s = underlineName.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 功能说明：下划线名称转换成驼峰名称(首字母大写).
     * <pre>转换过程将调用{@link CamelCaseUtils#toCamelCase(String)}方法</pre>
     * @param underlineName 传入的下划线名称.
     * @return String 返回驼峰名称(首字母大写).
     */
    public static String toCapitalizeCamelCase(final String underlineName) {
        if (underlineName == null) {
            return null;
        }
        String s = toCamelCase(underlineName);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 功能说明：下划线名称转成每个单词首字母联起来的名称.
     * <pre>该方法不会对大小写进行转换</pre>
     * @param underlineName 传入的下划线名称.
     * @return String 返回每个单词首字母联起来的名称.
     */
    public static String toCapitalizeCamelCaseAlias(final String underlineName) {
        if (underlineName == null) {
            return null;
        }
        String[] aa = underlineName.split("_");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < aa.length; i++) {
            sb.append(aa[i].charAt(0));
        }
        return sb.toString();
    }
}
