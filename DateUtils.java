package com.anfu.cloud.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {


    /**
     * 获取今天的日期
     * @return
     */
    public static String getNowDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Date date = cal.getTime();
        SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM");
        String dateStringYYYYMM = format1.format(date);
        SimpleDateFormat format11= new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD = format11.format(date);
        return dateStringYYYYMMDD;
    }

    /**
     * 获取昨天的日期
     * @return
     */
    public static String getPreviousDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        int year2 = cal.get(Calendar.YEAR);
        int month2 = cal.get(Calendar.MONTH)+1;
        int day2 = cal.get(Calendar.DAY_OF_MONTH);
        Date date2 = cal.getTime();
        SimpleDateFormat format2= new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD2 = format2.format(date2);
        return dateStringYYYYMMDD2;
    }

    /**
     * 获取上个月的今天的日期
     * @return
     */
    public static String getLastMonthOfDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        int year3 = cal.get(Calendar.YEAR);
        int month3 = cal.get(Calendar.MONTH)+1;
        int day3 = cal.get(Calendar.DAY_OF_MONTH);
        Date date3 = cal.getTime();
        SimpleDateFormat format3= new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD3 = format3.format(date3);
        return dateStringYYYYMMDD3;
    }

    //获取一个月天数
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String getLastDate(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String preMonth = dateFormat.format(c.getTime());
        return preMonth;
    }

}
