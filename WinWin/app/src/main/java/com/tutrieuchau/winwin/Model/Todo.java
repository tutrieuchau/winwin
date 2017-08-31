package com.tutrieuchau.winwin.Model;

/**
 * Created by ductam on 2017/08/31.
 */

public class Todo {
    public int icon;
    public int color;
    public String title;
    public String startTime;
    public Boolean bell;
    public Todo(String title,String startTime,int icon,int color){
        this.title = title;
        this.startTime = startTime;
        this.color = color;
        this.icon = icon;
        this.bell = true;
    }
}
