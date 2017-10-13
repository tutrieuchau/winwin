package com.tutrieuchau.winwin.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tutrieuchau.winwin.Model.Reminder;
import com.tutrieuchau.winwin.Model.TimeSpend;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ductam on 2017/08/30.
 */

public class SharePreferencesService {
    private final String REMINDER_PREFERENCE = "preferenceReminder";
    private final String TIME_SPEND_PREFERENCE = "preferenceTimeSpend";
    private Gson gson;
    private SharedPreferences sharedPreferences;
    public SharePreferencesService(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        gson = new Gson();
    }
    public void insertReminderList(ArrayList<Reminder> reminders){
        Gson gson = new Gson();
        String json = gson.toJson(reminders);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(REMINDER_PREFERENCE,json);
        editor.commit();
    }
    public ArrayList<Reminder> getReminderList(){
        String json = sharedPreferences.getString(REMINDER_PREFERENCE,null);
        if(json== null){
            return new ArrayList<>();
        }
        Type type  = new TypeToken<ArrayList<Reminder>>(){}.getType();
        ArrayList<Reminder> reminderList = gson.fromJson(json,type);
        return reminderList;
    }
    public void updateReminderList(int position,Reminder reminder){
        ArrayList<Reminder> reminderList = getReminderList();
        reminderList.remove(position);
        reminderList.add(position,reminder);
        insertReminderList(reminderList);
    }
    public void addItemToReminderList(Reminder reminder){
        ArrayList<Reminder> reminderList = getReminderList();
        reminderList.add(reminder);
        insertReminderList(reminderList);
    }
    public void removeItemInReminderList(int position){
        ArrayList<Reminder> reminderList = getReminderList();
        reminderList.remove(position);
        insertReminderList(reminderList);
    }

    //SpendTime Service
    public void insertSpendTimeList(ArrayList<TimeSpend> timeSpends){
        Gson gson = new Gson();
        String json = gson.toJson(timeSpends);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TIME_SPEND_PREFERENCE,json);
        editor.commit();
    }
    public ArrayList<TimeSpend> getSpendTimeList(){
        String json = sharedPreferences.getString(TIME_SPEND_PREFERENCE,null);
        if(json== null){
            return new ArrayList<>();
        }
        Type type  = new TypeToken<ArrayList<TimeSpend>>(){}.getType();
        ArrayList<TimeSpend> spendTimeList = gson.fromJson(json,type);
        return  spendTimeList;
    }
    public void updateSpendTimeList(int position,TimeSpend timeSpend){
        ArrayList<TimeSpend> spendTimeList = getSpendTimeList();
        spendTimeList.remove(position);
        spendTimeList.add(position,timeSpend);
        insertSpendTimeList(spendTimeList);
    }
    public void addItemToSpendTimeList(TimeSpend timeSpend){
        ArrayList<TimeSpend> spendTimeList = getSpendTimeList();
        spendTimeList.add(timeSpend);
        insertSpendTimeList(spendTimeList);
    }
    public void removeItemInSpendTimeList(int position){
        ArrayList<TimeSpend> spendTimeList = getSpendTimeList();
        spendTimeList.remove(position);
        insertSpendTimeList(spendTimeList);
    }

}
