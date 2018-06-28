package com.zy.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    
    private static final String salt = "1a2b3c4d";
    
    private MD5Util() {
        //工具类不需要外部实现
    }
    
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }
    
    
    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }
    
    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }
    
    public static void main(String[] args) {
        System.out.println(inputPassToDbPass("123456","1a2b3c4d"));
    }

}
