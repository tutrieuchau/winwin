package com.tutrieuchau.winwin.Service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tutrieuchau.winwin.Model.TimeSpend;
import com.tutrieuchau.winwin.Model.Todo;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ductam on 2017/08/30.
 */

public class SharePreferencesService {
    private final String TODO_PREFERENCE = "preferenceTodo";
    private final String TIME_SPEND_PREFERENCE = "preferenceTimeSpend";
    private Gson gson;
    private SharedPreferences sharedPreferences;
    public SharePreferencesService(Activity context){
        sharedPreferences = context.getPreferences(Context.MODE_PRIVATE);
        gson = new Gson();
    }
    public ArrayList<Todo> getTodoList(){
        String json = sharedPreferences.getString(TODO_PREFERENCE,null);
        Type type  = new TypeToken<ArrayList<Todo>>(){}.getType();
        ArrayList<Todo> todoArrayList = gson.fromJson(json,type);
        return  todoArrayList;
    }
    public void insertTodoList(ArrayList<Todo> todoList){

    }
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
            return new ArrayList<TimeSpend>();
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
