package com.tutrieuchau.winwin.Support;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.tutrieuchau.winwin.Model.TimeSpend;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by ductam on 2017/09/06.
 */

public class AddTimeDialog extends Dialog implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    private Context context;
    private EditText etName;
    private ImageView thumbnail;
    private NumberPicker npHour;
    private NumberPicker npMinute;
    private int hour;
    private int minute;
    private int selectedPosition;
    private OnAddTimeDialogResult dialogResult;
    private TimeSpend timeSpend;
    public AddTimeDialog(Context context,TimeSpend timeSpend){
        super(context);
        this.context = context;
        this.timeSpend = timeSpend;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int width = (context.getResources().getDisplayMetrics().widthPixels);
        setContentView(R.layout.time_add_dialog);
        getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        Button btnOK = (Button) findViewById(R.id.btnOK);
        btnOK.setOnClickListener(this);
        //init
        hour = 0;
        minute = 0;
        selectedPosition = 0;
        etName = (EditText)findViewById(R.id.etName);
        etName.setEnabled(false);
        //Number Picker
        npHour = (NumberPicker) findViewById(R.id.npHour);
        npHour.setMinValue(0);
        npHour.setMaxValue(23);
        npHour.setWrapSelectorWheel(true);
        npHour.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                hour = newVal;
            }
        });

        npMinute = (NumberPicker) findViewById(R.id.npMinute);
        String[] values = {"0","10","20","30","40","50"};
        npMinute.setMinValue(0);
        npMinute.setMaxValue(values.length - 1);
        npMinute.setDisplayedValues(values);
        npMinute.setWrapSelectorWheel(true);
        npMinute.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minute = newVal*10;
            }
        });
        // ImageView
        thumbnail = (ImageView)findViewById(R.id.imgThumbnail);
        // Spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spName);

        ArrayList<String> activityList = new ArrayList<>();
        for(String name : Utils.DEFAULT_THUMBNAIL_LIST_TITLE){
            activityList.add(name);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,activityList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(this);
        //For Edit
        if(timeSpend != null){
            spinner.setSelection(timeSpend.position);
            hour = timeSpend.spendTime/60;
            minute = timeSpend.spendTime%60;
            npHour.setValue(hour);
            npMinute.setValue(minute/10);
            if(timeSpend.position == Utils.DEFAULT_THUMBNAIL_LIST_TITLE.length -1){
                etName.setText(timeSpend.title);
                etName.setEnabled(true);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCancel){
            this.dismiss();
        }else if(v.getId() == R.id.btnOK){
            int time = hour*60 + minute;
            if(time == 0){
                showErrorMessage(context.getString(R.string.time_add_dialog_time_invalid_error));
            }else if(selectedPosition == Utils.DEFAULT_THUMBNAIL_LIST_TITLE.length - 1){
                if(etName.getText().toString().equals("")){
                    showErrorMessage(context.getString(R.string.time_add_dialog_name_invalid_error));
                }else{
                    dismiss();
                    //TODO:Show select ICON
                }
            }else{
                TimeSpend timeSpend = new TimeSpend(Utils.DEFAULT_THUMBNAIL_LIST_TITLE[selectedPosition],Utils.DEFAULT_THUMBNAIL_LIST_COLOR[selectedPosition],time,Utils.DEFAULT_THUMBNAIL_LIST_ICON[selectedPosition]);
                timeSpend.position = selectedPosition;
                dialogResult.finish(timeSpend);
                dismiss();
            }


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = Utils.DEFAULT_THUMBNAIL_LIST_TITLE[position];
        selectedPosition = position;
        if(selected.equals("Other")){
            etName.setEnabled(true);
            thumbnail.setImageResource(R.drawable.ic_extension);
            thumbnail.setBackgroundTintList(ContextCompat.getColorStateList(context,R.color.themeLight));
        }else{
            etName.setText("");
            etName.setEnabled(false);
            // change thumbnail
            thumbnail.setImageResource(Utils.DEFAULT_THUMBNAIL_LIST_ICON[position]);
            thumbnail.setBackgroundTintList(ContextCompat.getColorStateList(context,Utils.DEFAULT_THUMBNAIL_LIST_COLOR[position]));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void showErrorMessage(String msg){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        bottomSheetDialog.setContentView(R.layout.error_fragment);
        TextView textView = (TextView) bottomSheetDialog.findViewById(R.id.errMsg);
        textView.setText(msg);
        bottomSheetDialog.show();
    }
    public void setDialogResult(OnAddTimeDialogResult dialogResult){
        this.dialogResult = dialogResult;
    }
    public interface OnAddTimeDialogResult{
        void finish(TimeSpend timeSpend);
    }
}
