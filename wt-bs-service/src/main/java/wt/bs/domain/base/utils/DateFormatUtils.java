package wt.bs.domain.base.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtils {
    public static final String DATE_MODEL_1 = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_MODEL_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_MODEL_3 = "yyyy-MM-dd HH:mm";
    public static final String DATE_MODEL_4 = "yyyy-MM-dd HH";
    public static final String DATE_MODEL_5 = "yyyy-MM-dd";
    public static final String DATE_MODEL_6 = "yyyy-MM";
    public static final String DATE_MODEL_7 = "yyyyMMddHHmmss.SSS";
    public static final String DATE_MODEL_8 = "yyyyMMddHHmmss";
    public static final String DATE_MODEL_9 = "yyyyMMddHHmm";
    public static final String DATE_MODEL_10 = "yyyyMMddHH";
    public static final String DATE_MODEL_11 = "yyyyMMdd";
    public static final String DATE_MODEL_12 = "yyyyMM";
    public static final String DATE_MODEL_13 = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String DATE_MODEL_14 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_MODEL_15 = "yyyy/MM/dd HH:mm";
    public static final String DATE_MODEL_16 = "yyyy/MM/dd HH";
    public static final String DATE_MODEL_17 = "yyyy/MM/dd";
    public static final String DATE_MODEL_18 = "yyyy/MM";
    public static final String DATE_MODEL_19 = "yyyy";
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public DateFormatUtils() {
    }

    public static String getFormat(String dateStr) {
        String result = null;
        if(dateStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d+")) {
            result = "yyyy-MM-dd HH:mm:ss.SSS";
        } else if(dateStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            result = "yyyy-MM-dd HH:mm:ss";
        } else if(dateStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
            result = "yyyy-MM-dd HH:mm";
        } else if(dateStr.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}")) {
            result = "yyyy-MM-dd HH";
        } else if(dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            result = "yyyy-MM-dd";
        } else if(dateStr.matches("\\d{4}-\\d{2}")) {
            result = "yyyy-MM";
        } else if(dateStr.matches("\\d{14}.\\d+")) {
            result = "yyyyMMddHHmmss.SSS";
        } else if(dateStr.matches("\\d{14}")) {
            result = "yyyyMMddHHmmss";
        } else if(dateStr.matches("\\d{12}")) {
            result = "yyyyMMddHHmm";
        } else if(dateStr.matches("\\d{10}")) {
            result = "yyyyMMddHH";
        } else if(dateStr.matches("\\d{8}")) {
            result = "yyyyMMdd";
        } else if(dateStr.matches("\\d{6}")) {
            result = "yyyyMM";
        } else if(dateStr.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}.\\d+")) {
            result = "yyyy/MM/dd HH:mm:ss.SSS";
        } else if(dateStr.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            result = "yyyy/MM/dd HH:mm:ss";
        } else if(dateStr.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}")) {
            result = "yyyy/MM/dd HH:mm";
        } else if(dateStr.matches("\\d{4}/\\d{2}/\\d{2} \\d{2}")) {
            result = "yyyy/MM/dd HH";
        } else if(dateStr.matches("\\d{4}/\\d{2}/\\d{2}")) {
            result = "yyyy/MM/dd";
        } else if(dateStr.matches("\\d{4}/\\d{2}")) {
            result = "yyyy/MM";
        }

        return result;
    }

    public static String format(Date date, String format) {
        if(date == null) {
            return null;
        } else {
            String resultStr = "";
            String formatTemp = format;
            if(format == null || "".equals(format)) {
                formatTemp = "yyyy-MM-dd";
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(formatTemp);
            resultStr = dateFormat.format(date).trim();
            return resultStr;
        }
    }

    public static Date parse(String dateStr, String format) throws ParseException {
        String formatTemp = format;
        if(format == null || "".equals(format)) {
            formatTemp = "yyyy-MM-dd";
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(formatTemp);
        return dateFormat.parse(dateStr);
    }

    public static Date parse(String dateStr) throws ParseException {
        String format = getFormat(dateStr);
        return parse(dateStr, format);
    }

    public static Date getSpecialHms(Date date, int hours, int minutes, int seconds) {
        if(date == null) {
            return null;
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(11, hours);
            cal.set(12, minutes);
            cal.set(13, seconds);
            cal.set(14, 0);
            return cal.getTime();
        }
    }
}
