package com.zy.miaosha.util;

public class StringUtil {
    
    private StringUtil() {
        // 工具类不需要实现
    }
    
    public static boolean isEmpty(final String str) {
        return (str == null || str.length() <= 0 || "".equals(str.trim())) ? true : false;
    }

}
