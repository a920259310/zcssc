package com.springboot.zcssc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 123 on 2018/6/10.
 */
public class DateUtil {
    public static String PATTERN_DATE="yyyy-MM-dd";
    public static String PATTERN_DATE_TIME="yyyy-MM-dd HH:mm:ss";
    public static String PATTERN_DATE_TIME_="yyyy/MM/dd HH:mm:ss";
    public static String PATTERN_DATE_NOT="yyyyMMdd";

    public static int DATE_DIFF_TYPE_ONE = 1;
    public static int DATE_DIFF_TYPE_TWO = 2;
    public static int DATE_DIFF_TYPE_THREE = 3;
    public static int DATE_DIFF_TYPE_FOR = 4;
    public static int DATE_DIFF_TYPE_FIVE = 5;


    public static String formatDate(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date parseDate(String date,String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(date);
    }

    public static Date add(Date date,int ymd,int amout) throws ParseException {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(ymd,amout);
        Date rightNowTime = rightNow.getTime();
        return rightNowTime;
    }

    public static Long dateDiff(Date start,Date end,int type){
        long l = end.getTime() - start.getTime();

        if(type == 1){
            return l;
        }

        if(type == 2){
            return l/1000;
        }

        if(type == 3){
            return l/1000/60;
        }

        if(type == 4){
            return l/1000/60/60;
        }

        if(type == 5){
            return l/1000/60/60/24;
        }

        return null;
    }

    public static void main(String[] args) throws ParseException {
        Date add = add(new Date(), Calendar.DAY_OF_MONTH, -10);
        System.out.println(formatDate(add,PATTERN_DATE_TIME));
    }

}
