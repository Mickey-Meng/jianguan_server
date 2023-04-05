package com.ruoyi.common.utils.jianguan.zjrw;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ruoyi.common.core.domain.model.RightData;
import com.ruoyi.common.core.domain.model.SafePerData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/7/9 5:24 下午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class DateUtils {
    public static SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    public static SimpleDateFormat sdfBase = new SimpleDateFormat("yyyy-MM-dd");


    public static Date getNowDate() throws ParseException {
        Date date = new Date();
        String dates =sdfBase.format(date);
//        ParsePosition pos = new ParsePosition(8);
//        Date re = sdfBase.parse(dates,pos);

        return   sdfBase.parse(dates);
    }

    public static Date getNowToDate(DateTime dateTime) throws ParseException {
//        Date date = new Date();
        String dates =sdfBase.format(dateTime);
//        ParsePosition pos = new ParsePosition(8);
//        Date re = sdfBase.parse(dates,pos);
        return  sdfBase.parse(dates);
    }

    public static String getDate(String date) throws ParseException {

        if(StringUtil.isEmpty(date)||"null".equals(date)){
            return "";
        }
        return  sdfBase.format(sdf.parse(date));
    }

    public static Date getDateForCon(String date) throws ParseException {
         return sdfBase.parse(date);
    }

    public static boolean isToday(Date inputJudgeDate) {
        boolean flag = false;
        // 获取当前系统时间
        long longDate = System.currentTimeMillis();
        Date nowDate = new Date(longDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(nowDate);
        String subDate = format.substring(0, 10);
        // 定义每天的24h时间范围
        String beginTime = subDate + " 00:00:00";
        String endTime = subDate + " 23:59:59";
        Date paseBeginTime = null;
        Date paseEndTime = null;
        try {
            paseBeginTime = dateFormat.parse(beginTime);
            paseEndTime = dateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (inputJudgeDate.after(paseBeginTime) && inputJudgeDate.before(paseEndTime)) {
            flag = true;
        }
        return flag;
    }

    public  static  boolean isIn(Date inputJudgeDate, RightData rightData){
        if(inputJudgeDate.after(rightData.getSttime())&&rightData.getEndtime().after(inputJudgeDate)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static SafePerData abc(){
        SafePerData safePerData = new SafePerData();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天

        safePerData.setSttime(c.getTime());
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));

        safePerData.setEndtime(ca.getTime());
        return safePerData;
    }

    public static SafePerData getDay(Integer type){
        SafePerData safePerData = new SafePerData();
        Date today = new Date();
        safePerData.setEndtime(today);

        Calendar beginCal = Calendar.getInstance();
        beginCal.setTime(today);
        //当type 为 1时，从今天起，向前推30天
        if (type == 1){
            beginCal.add(Calendar.DAY_OF_YEAR,-30); //日期往前推30天
        } else if (type == 3){
            beginCal.add(Calendar.DAY_OF_YEAR,-90); //日期往前推90天
        }
        safePerData.setSttime(beginCal.getTime());

        return safePerData;
    }


    public static SafePerData getStAndEndByMonth(SafePerData safePerData) throws ParseException {
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd");
        instance.setTime(safePerData.getAskTime());
        instance.set(Calendar.DAY_OF_MONTH,1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        String format = formatTemp.format(instance.getTime());
        Date dateSt = formatTemp.parse(format);
        //设置开始
        safePerData.setSttime(dateSt);
        instance.set(Calendar.DATE, instance.getActualMaximum(Calendar.DATE));
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        format = formatTemp.format(instance.getTime());
        Date dateEnd = formatTemp.parse(format);
        safePerData.setEndtime(dateEnd);
        return safePerData;
    }

    public static Date getDateByStr(String date) throws ParseException {
        SimpleDateFormat week = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = week.parse(date);
        return parse;
    }

    public static String getDateByDate(Date date){
        SimpleDateFormat week = new SimpleDateFormat("yyyy-MM-dd");
        String format = week.format(date);
        return format;
    }

    public static List<SafePerData> getFourDateByType(String askTime, int size) throws ParseException {
        SimpleDateFormat week = new SimpleDateFormat("yyyy-MM-dd");
        List<SafePerData> safePerDataList = Lists.newArrayList();
        if(size == 3){
            Date parse = week.parse(askTime);
            for (int i =0;i<4;i++){
                Date monday = getWeekByDate(parse,Calendar.MONDAY);
                Date sunday = getWeekByDate(parse,Calendar.SUNDAY);
                SafePerData safePerData =new SafePerData();
                safePerData.setSttime(monday);
                safePerData.setEndtime(sunday);
                safePerDataList.add(safePerData);
                //将parse 减去7天
                parse = reduceSevenDays(parse);
            }
        }else{
            throw new ParseException(askTime,size);
        }
        return safePerDataList;
    }

    private static Date reduceOneMonth(Date parse) throws ParseException {
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date=Calendar.getInstance();
        date.setTime(parse);
        date.add(Calendar.MONTH, -1);
        //修改
//        date.set(Calendar.MONTH, date.get(Calendar.MONTH)-1);
        String format = formatTemp.format(date.getTime());
        return formatTemp.parse(format);
    }

    private static Date reduceOneMonth(Date parse,int i) throws ParseException {
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM");
        Calendar date=Calendar.getInstance();
        date.setTime(parse);
        date.add(Calendar.MONTH, -1*i);
//        date.set(Calendar.MONTH, date.get(Calendar.MONTH)-i);
        String format = formatTemp.format(date.getTime());
        System.out.println(format);
        return formatTemp.parse(format);
    }



    private static Date reduceSevenDays(Date parse) throws ParseException {
        SimpleDateFormat formatTemp = new SimpleDateFormat("yyyy-MM-dd");
        Calendar instance = new GregorianCalendar();
        instance.setTime(parse);
        instance.add(Calendar.SECOND, -7*24*3600);
        String format = formatTemp.format(instance.getTime());
        Date day = formatTemp.parse(format);
        return day;
    }

    //根据时间获取周一
    private static Date getWeekByDate(Date date, int a) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
        instance.setTime(date);
        instance.set(Calendar.DAY_OF_WEEK, a);//周一
        if(Calendar.MONDAY == a){
            instance.set(Calendar.HOUR_OF_DAY, 0);
            instance.set(Calendar.MINUTE, 0);
            instance.set(Calendar.SECOND, 0);
            instance.set(Calendar.MILLISECOND, 0);
        }else if(Calendar.SUNDAY == a){
            instance.set(Calendar.HOUR_OF_DAY, 23);
            instance.set(Calendar.MINUTE, 59);
            instance.set(Calendar.SECOND, 59);
            instance.set(Calendar.MILLISECOND, 999);
        }
        String format = target.format(instance.getTime());
        System.out.println(format);
        Date day = target.parse(format);

        return day;
    }

    public static int day(int month,int year){
        int a=0;
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                a=31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                a=30;
                break;
            case 2:
                if((year%4==0&&year%100!=0)||year%400==0){
                    a=29;
                }else{
                    a=28;
                }
        }
        return a;
    }

    public static Date getEarlyDate(String askTime) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = target.parse(askTime);
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        String format = target.format(instance.getTime());
        Date day = target.parse(format);
        return day;
    }

    public static Date getlateDate(String askTime) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = target.parse(askTime);
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(date);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        String format = target.format(instance.getTime());
        Date day = target.parse(format);
        return day;
    }

    public static Date getStCheckDate(Date sttime) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(sttime);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        String format = target.format(instance.getTime());
        Date day = target.parse(format);
        return day;
    }

    public static Date getEndCheckDate(Date sttime) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(sttime);
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        String format = target.format(instance.getTime());
        Date day = target.parse(format);
        return day;
    }

    public static Map<Integer, SafePerData> getCount(String type) throws ParseException {
        Map<Integer, SafePerData>  mapDate = Maps.newHashMap();
        Date  date = new Date();
        //周传week，月传month，季传season 返回6个时间段
        if("week".equals(type)){
            for (int i =0;i<6;i++){
                Date monday = getWeekByDate(date,Calendar.MONDAY);
                Date sunday = getWeekByDate(date,Calendar.SUNDAY);
                SafePerData safePerData =new SafePerData();
                safePerData.setSttime(monday);
                safePerData.setEndtime(sunday);
                mapDate.put(i+1,safePerData);
                //将parse 减去7天
                date = reduceSevenDays(date);
            }
        }else if("month".equals(type)){
            for (int i =0;i<6;i++){
                //获取当前月的最早一天
                Date firstMonthDay = firstMonthDay(date);
                //获取当前月的最后一天
                Date lastMonthDay = lasttMonthDay(date);
                SafePerData safePerData =new SafePerData();
                safePerData.setSttime(firstMonthDay);
                safePerData.setEndtime(lastMonthDay);
                mapDate.put(i+1,safePerData);
                //减去当前月减去一个月
                date = reduceOneMonth(date,1);
            }
        }else if("season".equals(type)){
            for (int i =0;i<6;i++){
                //获取当前月的最早一天
                Date firstMonthDay = firstMonthDay(date);
                //获取当前月的最后一天
                Date lastMonthDay = lasttMonthDay(date);
                SafePerData safePerData =new SafePerData();
                safePerData.setSttime(firstMonthDay);
                safePerData.setEndtime(lastMonthDay);
                mapDate.put(i+1,safePerData);
                //减去当前月减去一个月
                date = reduceOneMonth(date,3);
            }
        }
        return mapDate;
    }

    private static Date firstMonthDay(Date date) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(date);
        instance.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        instance.set(Calendar.DAY_OF_MONTH,1);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.SECOND, 0);
        instance.set(Calendar.MILLISECOND, 0);
        String format = target.format(instance.getTime());
        Date day = target.parse(format);
        return day;
    }

    private static Date lasttMonthDay(Date date) throws ParseException {
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTime(date);
        instance.add(Calendar.MONTH, 0);
        //当前日期既为本月最后一天
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));
        instance.set(Calendar.HOUR_OF_DAY, 23);
        instance.set(Calendar.MINUTE, 59);
        instance.set(Calendar.SECOND, 59);
        instance.set(Calendar.MILLISECOND, 999);
        String format = target.format(instance.getTime());
        Date day = target.parse(format);
        return day;
    }

    public static String getDateByDateAndType(Date sttime, String type) {
        //周传week，月传month，季传season 返回6个时间段
        if("week".equals(type)){
            SimpleDateFormat week = new SimpleDateFormat("yyyy-MM-dd");
            return week.format(sttime);
        }else{
            SimpleDateFormat week = new SimpleDateFormat("yyyy-MM");
            return week.format(sttime);
        }
    }

    public static SafePerData getMonthStAndEnd(Date date) throws ParseException {
        Date last =lasttMonthDay(date);
        Date early = firstMonthDay(date);
        SafePerData safePerData =new SafePerData();
        safePerData.setSttime(early);
        safePerData.setEndtime(last);
        return safePerData;
    }

    public static SafePerData getDayStAndEnd(Date date) throws ParseException {
        Date stCheckDate = getStCheckDate(date);
        Date endCheckDate = getEndCheckDate(date);
        SafePerData safePerData =new SafePerData();
        safePerData.setSttime(stCheckDate);
        safePerData.setEndtime(endCheckDate);
        return safePerData;
    }

    public static SafePerData abcThree() {
        SafePerData safePerData = new SafePerData();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -2);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天

        safePerData.setSttime(c.getTime());


//获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));

        safePerData.setEndtime(ca.getTime());
        return safePerData;
    }

    public static String setWeekName(String year, int type) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,Integer.parseInt(year));
        cal.set(Calendar.WEEK_OF_YEAR, type + 1); // 设置为2016年的第10周
        cal.set(Calendar.DAY_OF_WEEK, 2);
        Date time = cal.getTime();
        return sdfBase.format(time);
    }

    public static String setMonthName(String year, int type) {
        return year+"-"+type;
    }

    public static String setSeasonName(String year, int type) {
        if(1==type){
            return year+"-"+1;
        }else if(2==type){
            return year+"-"+4;
        }else if(3==type){
            return year+"-"+7;
        }else {
            return year+"-"+10;
        }
    }

    public static SafePerData abcc(String date) {
        SafePerData safePerData = new SafePerData();
        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        safePerData.setSttime(cal.getTime());

        int actualMaximum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        cal.set(Calendar.DAY_OF_MONTH, actualMaximum);
        safePerData.setEndtime(cal.getTime());
        return safePerData;
    }



    public static SafePerData abccc(String date) {

        SafePerData safePerData = new SafePerData();
        String[] split = date.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.DAY_OF_MONTH,day);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        safePerData.setSttime(cal.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        safePerData.setEndtime(cal.getTime());
        return safePerData;
    }

    public static String toDay(Date date1) {
        String parse = sdfBase.format(date1);
        return parse.split("-")[2];

    }

    /**
     * 获取当天开始时间
     * @return
     */
    public static Date getDayBegin() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当天结束时间
     * @return
     */
    public static Date getDayEnd() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public static int subtractTwoDate(String startTime, String endTime){
        String[] star1 = startTime.split("-");
        String[] end1 = endTime.split("-");
        int days = 0;
        if(Integer.parseInt(star1[0]) < Integer.parseInt(end1[0])){
            for(int i = Integer.parseInt(star1[0]); i < Integer.parseInt(end1[0]); i++){
                //计算是否是瑞年
                if(i % 4 == 0 && i % 100 != 0 || i % 400 == 0){
                    days += 366;
                }else{
                    days += 365;
                }
            }
        }
        //得到开始那一年已过去的日期
        int startDay= days(star1[0], star1[1], star1[2]);
        //得到结束那一年已过去的日期
        int endDay= days(end1[0], end1[1], end1[2]);
        //减去开始那一年已过去的日期，加上结束那一年已过去的日期
        days = days - startDay + endDay;

        return days;
    }

    /**
     * 年月日
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static int days(String year, String month, String day){
        int days = 0;
        int nowyear = Integer.parseInt(year);
        int[] monthday = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        int[] monthday1 = {0,31,29,31,30,31,30,31,31,30,31,30,31};
        boolean flag = true;
        if(nowyear % 4 == 0 && nowyear % 100 != 0 || nowyear % 400 == 0){
        }else{
            flag = false;
        }
        for(int i=0;i < Integer.parseInt(month); i++){
            if(flag){
                days += monthday1[i];
            }else{
                days += monthday[i];
            }
        }
        days += Integer.parseInt(day);
        return days;
    }

    /**
     * 获取指定日期所在月的第一天
     * @param date 日期
     * @return 所在月的第一天
     */
    public static Date getFirstOfMonth(Date date){
        //获取当前月第一天：
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return calendar.getTime();
    }

    /**
     * 获取指定日期所在月的第最后一天
     * @param date 日期
     * @return  最后一天
     */
    public static Date getLastOfMonth(Date date) {
        //获取当前月最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    //判断选择的日期是否是本月
    public static boolean inCurrentMonth(Date givenDate) {
        Date today = new Date();
        return givenDate.getMonth() == today.getMonth() && givenDate.getYear() == today.getYear();

    }
}
