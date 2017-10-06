package com.tutrieuchau.winwin.Activity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Common;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout timeGroup;
    private TextView time;
    private ImageView thumbnail;
    private Spinner timeBefore;
    private Spinner rewardType;
    private EditText coin;
    private ImageView nextDay;
    private ImageView preDay;
    private EditText rewardOther;
    private TextView month;
    private TextView day;
    private Calendar targetCalendar;
    private RelativeLayout monthDayGroup;
    private ImageView back;
    private ImageView save;
    private Animation fadeOutToRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_reminder);
        targetCalendar = Calendar.getInstance();
        //init
        timeGroup = (LinearLayout) findViewById(R.id.reminderAddTimeGroup);
        timeGroup.setOnClickListener(this);
        time = (TextView)findViewById(R.id.reminderAddTime);
        thumbnail = (ImageView) findViewById(R.id.reminderAddThumbnail);
        thumbnail.setOnClickListener(this);
        timeBefore = (Spinner) findViewById(R.id.reminderAddTimeBefore);
        rewardType = (Spinner) findViewById(R.id.reminderAddRewardTypeSpinner);
        coin = (EditText) findViewById(R.id.reminderAddRewardCoin);
        nextDay = (ImageView) findViewById(R.id.reminderAddNextDay);
        nextDay.setOnClickListener(this);
        preDay = (ImageView) findViewById(R.id.reminderAddPreDay);
        preDay.setOnClickListener(this);
        rewardOther = (EditText)findViewById(R.id.reminderAddRewardOther);
        rewardOther.setVisibility(View.GONE);
        month = (TextView)findViewById(R.id.reminderAddMonth);
        month.setText(Utils.MONTH_SHORT_EN[targetCalendar.get(Calendar.MONTH)]);
        day = (TextView)findViewById(R.id.reminderAddDay);
        day.setText(Common.getDateStringFormatEn(targetCalendar));
        monthDayGroup = (RelativeLayout)findViewById(R.id.reminderAddMonthDayGroup);
        //Navigation
        back = (ImageView) findViewById(R.id.reminderAddBack);
        back.setOnClickListener(this);
        save = (ImageView) findViewById(R.id.reminderAddSave);
        save.setOnClickListener(this);
        // init Local
        final ImageView coinIcon = (ImageView) findViewById(R.id.reminderAddCoinIcon);
//        rewardType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(position == 1){
//                    coin.setVisibility(View.GONE);
//                    coinIcon.setVisibility(View.GONE);
//
//                    rewardOther.setVisibility(View.VISIBLE);
//                }else {
//                    rewardOther.setVisibility(View.GONE);
//
//                    coin.setVisibility(View.VISIBLE);
//                    coinIcon.setVisibility(View.VISIBLE);
//                }
//            }
//        });


        //Load Animation
        fadeOutToRight = AnimationUtils.loadAnimation(this,R.anim.out_to_right);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.reminderAddTimeGroup:
                Calendar calendar = Calendar.getInstance();
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText(hourOfDay+":"+minute);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.reminderAddBack:
                finish();
                break;
            case R.id.reminderAddSave:
                onSaveReminder();
                finish();
                break;
            case R.id.reminderAddThumbnail:

                break;
            case R.id.reminderAddNextDay:
                monthDayGroup.startAnimation(fadeOutToRight);
                break;
            case R.id.reminderAddPreDay:

                break;
        }
    }

    private void onSaveReminder(){
        //ToDo: on Save Reminder
    }



}
