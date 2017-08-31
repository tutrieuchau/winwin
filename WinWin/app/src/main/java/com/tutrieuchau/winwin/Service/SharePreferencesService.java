package com.tutrieuchau.winwin.Service;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tutrieuchau.winwin.Model.Todo;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by ductam on 2017/08/30.
 */

public class SharePreferencesService {
    private final String TODO_PREFERENCE = "preferenceTodo";
    private final String TIME_SPEND_PREFERENCE = "preferenceTodo";
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
}
