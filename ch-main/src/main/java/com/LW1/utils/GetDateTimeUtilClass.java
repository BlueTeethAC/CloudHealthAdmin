package com.LW1.utils;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;

// 时间相关的工具类
@UtilityClass
public class GetDateTimeUtilClass {

    // 获取当前时间的方法
    public String getNowDateTime(){
        Date nowDate = new Date();// 获取当前的时间对象

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 实例化SimpleDateFormate对象，并设置显示格式
        String formatDateTime = format.format(nowDate);// 将时间对象转换为定义好的格式

        return formatDateTime;
    }

}
