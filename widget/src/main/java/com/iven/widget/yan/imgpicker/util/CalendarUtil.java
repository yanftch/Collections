package com.iven.widget.yan.imgpicker.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    private static CalendarUtil instance = null;//fullDateFormatNoConn
    public final static SimpleDateFormat FULLDATEFORMATNOCONN = new SimpleDateFormat("yyyyMM_ddHHmmss");
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public final static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");




    public synchronized static CalendarUtil createInstance() {
        if (instance == null) {
            instance = new CalendarUtil();
        }
        return instance;
    }

    public String getYyyyMmDd(Calendar cal) {
        StringBuilder sb = new StringBuilder();
        sb.append(cal.get(Calendar.YEAR)).append("年")
                .append(cal.get(Calendar.MONTH) + 1).append("月")
                .append(cal.get(Calendar.DAY_OF_MONTH)).append("日");
        return sb.toString();
    }

    // yyyy-MM-dd HH:mm:ss
    public String getTime(String format) {
        Date nowTime = new Date();
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.format(nowTime);
    }

    //讲字符串转换成指定的时间格式
    public Date getDate(String format) {
        Date date = null;
        try {
            date = dateFormat.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date getDate2(String format) {
        Date date = null;
        try {
            date = dateFormat2.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public final static SimpleDateFormat dateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public Date getDate3(String format) {
        Date date = null;
        try {
            date = dateFormat3.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    //根据指定格式 获取指定时间
    public String getDayTime(String format, int daty) {
        Date nowTime = new Date(new Date().getTime() - daty * 24 * 60 * 60 * 1000);
        SimpleDateFormat time = new SimpleDateFormat(format);
        return time.format(nowTime);
    }

    public String dataToString(DateFormat dateFormat, Date date, String cucId, String userId, String type) {
        if (cucId != null && userId != null && type != null)
            return dateFormat.format(date) + "__" + cucId + "__" + userId + "__" + type + "__" + ".jpg";
        else
            return dateFormat.format(date) + ".jpg";
    }
}
