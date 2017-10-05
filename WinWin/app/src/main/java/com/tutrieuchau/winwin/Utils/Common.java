package com.tutrieuchau.winwin.Utils;

import com.tutrieuchau.winwin.R;

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
}
