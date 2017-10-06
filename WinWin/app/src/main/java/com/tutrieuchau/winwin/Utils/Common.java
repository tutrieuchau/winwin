package com.tutrieuchau.winwin.Utils;

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
        result = result + minute;
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
}
