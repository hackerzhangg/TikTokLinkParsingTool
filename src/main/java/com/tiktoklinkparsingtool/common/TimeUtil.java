package com.tiktoklinkparsingtool.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zgp
 * @Since 2021 -09 -11 01 :24
 * @Description 时间工具类
 */
public class TimeUtil {

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowDay(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
