package com.suguiming.myandroid.tool.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by suguiming on 15/11/18.
 */
public class DateUtil {

    //把1 变成 01
    public static String getNumStrig(int num) {
        if (0 <= num && num <= 9) {
            return "0" + num;
        } else {
            return num + "";
        }
    }

    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);//24小时制
    }

    public static int getCurrentMinute() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MINUTE);
    }

    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {//得到的month 是1到12
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayNums(int year, int month) {//某月有多少天,month传1到12
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29; // 闰年2月29天
        }
         return arr[month - 1];
    }

    public static void test1(int year, int month){

        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");


        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, 1);//时期设置成1
        cal.add(Calendar.MONTH, 1);//月份加1
        cal.set(Calendar.DAY_OF_MONTH, -1);//再减一天。
        Date lastDate = cal.getTime();





//        Calendar   cDay1   =   Calendar.getInstance();
//        cDay1.setTime(sDate1);
//        final   int   lastDay   =   cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
//        Date   lastDate   =   cDay1.getTime();
//        lastDate.setDate(lastDay);
    }

    public static long getGapDays(Date startDate, Date endDate) {//相差天数
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
    }


}
