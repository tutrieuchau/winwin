package com.tutrieuchau.winwin.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tutrieuchau.winwin.Activity.TodoActivity;
import com.tutrieuchau.winwin.Model.Todo;
import com.tutrieuchau.winwin.R;

import java.util.ArrayList;

/**
 * Created by ductam on 2017/08/31.
 */

public class TodoAdapter extends ArrayAdapter<Todo> {
    private Context context;
    private ArrayList<Todo> dataSet;
    public TodoAdapter(Context context, ArrayList<Todo> data){
        super(context, R.layout.todo_row_item,data);
        this.context = context;
        this.dataSet = data;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Todo todo = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.todo_row_item, parent, false);
        }
        ImageView thumbnails = (ImageView) convertView.findViewById(R.id.todoThumbnail);
        thumbnails.setImageResource(todo.icon);
        thumbnails.setBackgroundTintList(ContextCompat.getColorStateList(context,todo.color));

        TextView title = (TextView) convertView.findViewById(R.id.tvTodoTitle);
        title.setText(todo.title);
        TextView time = (TextView)convertView.findViewById(R.id.tvTodoStart);
        time.setText(todo.startTime);
        ImageButton btnBell = (ImageButton) convertView.findViewById(R.id.btnBell);
        Drawable drawable = context.getResources().getDrawable(R.drawable.ic_bell);

        if(todo.bell){
            drawable.setColorFilter(context.getResources().getColor(R.color.themeDark), PorterDuff.Mode.MULTIPLY);
        }else{
            drawable.setColorFilter(context.getResources().getColor(R.color.themeOff), PorterDuff.Mode.MULTIPLY);
        }
        btnBell.setImageDrawable(drawable);
        final ImageButton btnModifier = (ImageButton) convertView.findViewById(R.id.btnModifier);
        btnModifier.setTag(position);
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PopupMenu popupMenu = new PopupMenu(context,btnModifier);
//                popupMenu.getMenuInflater().inflate(R.menu.popup_todo,popupMenu.getMenu());
//                popupMenu.show();
                int[] location  = new int[2];
                v.getLocationOnScreen(location);
                Point point = new Point();
                point.x = location[0];
                point.y = location[1];

                showPopupMenu(point);
            }
        });

        return convertView;
    }
    private void showPopupMenu(Point point){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.time_row_item, null);
        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(layout);
        popupWindow.setWidth(200);
        popupWindow.setHeight(100);
        popupWindow.showAtLocation(layout, Gravity.NO_GRAVITY,point.x,point.y);
    }
}
