package com.tutrieuchau.winwin.Adapter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.tutrieuchau.winwin.Fragment.ReminderFragment;
import com.tutrieuchau.winwin.Model.Reminder;
import com.tutrieuchau.winwin.Model.TimeSpend;
import com.tutrieuchau.winwin.Model.Todo;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Support.ConfirmDialog;
import com.tutrieuchau.winwin.Utils.Common;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by tutr on 9/18/2017.
 */

public class ReminderAdapter extends ArrayAdapter<Reminder> {
    private ArrayList<Reminder> dataSet;
    private Context context;
    private ReminderAdapterOnItemSelectListener onItemSelectListener;
    public ReminderAdapter(Context context, ArrayList<Reminder> data){
        super(context, R.layout.fragment_remider_row_item,data);
        this.context = context;
        this.dataSet = data;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
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

        // Alarm
        TextView alarmBefore = (TextView) convertView.findViewById(R.id.reminderAlarmBefore);
        alarmBefore.setText(Common.getAlarmBeforeTextFromEnum(reminder.alarmBefore));

        // Button Modifier
        final ImageView btnMenu = (ImageView) convertView.findViewById(R.id.reminderItemMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,btnMenu);
                popupMenu.getMenuInflater().inflate(R.menu.reminder_item_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.reminder_done:
                                ConfirmDialog doneDialog = new ConfirmDialog(context,null,"You are already done it!",null,"Yes, I done it!");
                                doneDialog.show();
                                doneDialog.setOnClickListener(new ConfirmDialog.ConfirmDialogOnClickListener() {
                                    @Override
                                    public void onClick(int id) {
                                        if(id == R.id.confirmDialogAgree){
                                            if(onItemSelectListener!= null){
                                                onItemSelectListener.onItemSelectedListener(POPUPITEM.DONE,position);
                                            }
                                        }
                                    }
                                });
                                break;
                            case R.id.reminder_edit:
                                if(onItemSelectListener!= null){
                                    onItemSelectListener.onItemSelectedListener(POPUPITEM.EDIT,position);
                                }
                                break;
                            case R.id.reminder_delete:
                                final ConfirmDialog confirmDialog = new ConfirmDialog(context);
                                confirmDialog.show();
                                confirmDialog.setOnClickListener(new ConfirmDialog.ConfirmDialogOnClickListener() {
                                    @Override
                                    public void onClick(int id) {
                                        if(id == R.id.confirmDialogAgree){
                                            if(onItemSelectListener!= null){
                                                onItemSelectListener.onItemSelectedListener(POPUPITEM.DELETE,position);
                                            }
                                        }
                                    }
                                });
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        return convertView;
    }
    public void setOnItemSelectedListener(ReminderAdapterOnItemSelectListener onItemSelectedListener){
        this.onItemSelectListener = onItemSelectedListener;
    }
    public interface ReminderAdapterOnItemSelectListener{
        void onItemSelectedListener(POPUPITEM type, int position);
    }
    public enum POPUPITEM{DONE,EDIT,DELETE}

}
