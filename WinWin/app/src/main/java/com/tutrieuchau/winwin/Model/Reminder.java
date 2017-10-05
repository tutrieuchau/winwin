package com.tutrieuchau.winwin.Model;

import com.tutrieuchau.winwin.Utils.Utils;

/**
 * Created by tutr on 9/18/2017.
 */

public class Reminder {
    public int thumbnail;
    public String title;
    public String reward;
    public int time;
    public enum ALARM_TIME{INTIME,BEFORE5,BEFORE10,BEFORE30,BEFORE45,BEFORE60}
    public boolean isComplete;
    public ALARM_TIME alarmBefore;

    public Reminder(int thumbnail, String title, String reward, int time, ALARM_TIME alarmBefore, boolean isComplete) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.reward = reward;
        this.time = time;
        this.alarmBefore = alarmBefore;
        this.isComplete = isComplete;
    }
}
