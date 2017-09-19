package com.tutrieuchau.winwin.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.tutrieuchau.winwin.Model.Reminder;
import com.tutrieuchau.winwin.Model.TimeSpend;
import com.tutrieuchau.winwin.Model.Todo;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by tutr on 9/18/2017.
 */

public class ReminderAdapter extends ArrayAdapter<Reminder> {
    private ArrayList<Reminder> dataSet;
    private Context context;
    public ReminderAdapter(Context context, ArrayList<Reminder> data){
        super(context, R.layout.fragment_remider_row_item,data);
        this.context = context;
        this.dataSet = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Reminder reminder = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_remider_row_item, parent, false);
        }
        //Thumbnail
        ImageView thumbnail = (ImageView) convertView.findViewById(R.id.reminderThumbnail);
        thumbnail.setImageResource(reminder.icon);
        Drawable drawable = context.getResources().getDrawable(R.drawable.btn_rectangle);
        drawable.setColorFilter(context.getResources().getColor(reminder.color), PorterDuff.Mode.SRC_IN);
        thumbnail.setBackgroundDrawable(drawable);

        //Title
        TextView title = (TextView) convertView.findViewById(R.id.tvReminderTitle);
        title.setText(reminder.title);
        // Start Time
        TextView startTime = (TextView) convertView.findViewById(R.id.tvReminderStart);
        startTime.setText(reminder.startTime);

        //Bound
        Button bound = (Button) convertView.findViewById(R.id.btnBound);
        bound.setText(String.valueOf(reminder.bound));

        //Active
        Switch switchActive = (Switch) convertView.findViewById(R.id.switchActive);
        switchActive.setChecked(reminder.bell);

        //Level
        Button level = (Button) convertView.findViewById(R.id.btnLevel);
        Drawable ovalDrawable = context.getResources().getDrawable(R.drawable.btn_oval);
        switch (reminder.level){
            case HIGH:
                ovalDrawable.setColorFilter(context.getResources().getColor(R.color.red), PorterDuff.Mode.SRC_IN);
                level.setText("1");
                break;
            case NORMAL:
                ovalDrawable.setColorFilter(context.getResources().getColor(R.color.gold), PorterDuff.Mode.SRC_IN);
                level.setText("2");
                break;
            case LOW:
                ovalDrawable.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                level.setText("3");
                break;
            default:
                ovalDrawable.setColorFilter(context.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                level.setText("3");
                break;
        }
        level.setBackgroundDrawable(ovalDrawable);


        return convertView;
    }
}
