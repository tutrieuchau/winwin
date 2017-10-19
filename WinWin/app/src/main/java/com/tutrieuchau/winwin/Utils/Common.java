package com.tutrieuchau.winwin.Utils;

import com.tutrieuchau.winwin.Model.Reminder;
import com.tutrieuchau.winwin.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by tutr on 9/25/2017.
 */

public class Common {
    public static int getThumbnailIcon(Utils.DEFAULT_ICON name){
        switch (name){
            case LEARNING:
                return R.drawable.ic_graduate;
            default:
                return R.drawable.ic_graduate;
        }
    }
    public static int getThumbnailColor(Utils.DEFAULT_ICON name){
        switch (name){
            case LEARNING:
                return R.color.themeLight;
            default:
                return R.color.themeLight;
        }
    }
    // Reminder fragment
    public static String getStringTimeByTime(int time){
        String result;
        int hour = time/60;
            if(hour < 10){
                result = "0"+ hour + ":";
            }else{
                result = hour + ":";
            }
        int minute = time%60;
            if(minute < 10){
                result = result + "0" + minute;
            }else{
                result = result + minute;
            }
        return result;
    }
    public static String getMonthShortFromDate(Date date){
        int month = date.getMonth() + 1;
        return Utils.MONTH_SHORT_EN[month];
    }
    public static String getDateStringFormatEn(Calendar calendar){
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if(day == 1){
            return "1st";
        }else if(day == 2){
            return "2nd";
        }else if(day == 3){
            return "3rd";
        }else {
            return day + "th";
        }
    }
    public static String getCurentTimeString(){
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE);
        return getStringTimeByTime(time);
    }
    public static int getTimeByString(String strTime){
        String[] timeStrs = strTime.split(":");
        int time = Integer.valueOf(timeStrs[0])*60 + Integer.valueOf(timeStrs[1]);
        return time;
    }
    public static String getAlarmBeforeTextFromEnum(Reminder.ALARM_TIME alarm){
        switch (alarm){
            case NOT_ALARM:
                return "Not Alarm";
            case INTIME:
                return "In Time";
            case BEFORE5:
                return "Before 5 Minute";
            case BEFORE10:
                return "Before 10 Minute";
            case BEFORE30:
                return "Before 30 Minute";
            case BEFORE45:
                return "Before 45 Minute";
            case BEFORE60:
                return "Before 60 Minute";
            default:
                return "Not Alarm";
        }
    }
}
