package com.tutrieuchau.winwin.Model;

/**
 * Created by ductam on 2017/08/30.
 */

public class TimeSpend {
    public String title;
    public int color;
    public int spendTime;// minute
    public int icon;
    public TimeSpend(String title, int color, int spendTime , int icon){
        this.title = title;
        this.color = color;
        this.spendTime = spendTime;
        this.icon = icon;
    }
}
