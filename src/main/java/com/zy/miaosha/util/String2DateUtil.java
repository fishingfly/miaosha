package com.zy.miaosha.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class String2DateUtil {
    
    private String2DateUtil() {
        // 工具类不需要外部实现直接用
    }
    
    public static Date stringTransferToDate(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

}
