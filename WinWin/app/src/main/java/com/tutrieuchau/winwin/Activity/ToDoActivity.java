package com.tutrieuchau.winwin.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.tutrieuchau.winwin.Adapter.TodoAdapter;
import com.tutrieuchau.winwin.Model.Todo;
import com.tutrieuchau.winwin.R;

import java.util.ArrayList;

public class TodoActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Todo todo = new Todo("Go to the market","14:30", R.drawable.ic_time_eat,R.color.bisque);
        ListView listView = (ListView) findViewById(R.id.todoListView);
        ArrayList<Todo> arrayList = new ArrayList<>();
        arrayList.add(todo);
        todo.color = R.color.chocolate;
        arrayList.add(todo);
        arrayList.add(todo);
        TodoAdapter todoAdapter = new TodoAdapter(this,arrayList);
        listView.setAdapter(todoAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnModifier){
            Toast.makeText(this,"Click on Position: "+ v.getTag(),Toast.LENGTH_LONG).show();

        }
    }

}
