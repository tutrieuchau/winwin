package com.tutrieuchau.winwin.Model;

import com.tutrieuchau.winwin.Utils.Utils;

/**
 * Created by tutr on 9/18/2017.
 */

public class Reminder {
    public int icon;
    public int color;
    public String title;
    public String startTime;
    public Boolean bell;
    public int bound;
    public Utils.REMINDER_LEVEL level;
    public Reminder(String title,String startTime,int icon,int color,int bound,Utils.REMINDER_LEVEL level){
        this.title = title;
        this.startTime = startTime;
        this.color = color;
        this.icon = icon;
        this.bell = true;
        this.bound = bound;
        this.level = level;
    }
}
