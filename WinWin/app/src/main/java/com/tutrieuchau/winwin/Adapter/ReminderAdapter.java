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
import com.tutrieuchau.winwin.Utils.Common;
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
        thumbnail.setImageResource(reminder.thumbnail);

        //Title
        TextView title = (TextView) convertView.findViewById(R.id.reminderTitle);
        title.setText(reminder.title);
        // Start Time
        TextView startTime = (TextView) convertView.findViewById(R.id.reminderAlarm);
        startTime.setText(Common.getStringTimeByTime(reminder.time));

        TextView reward = (TextView) convertView.findViewById(R.id.reminderReward);
        reward.setText(reminder.reward);

        return convertView;
    }
}
