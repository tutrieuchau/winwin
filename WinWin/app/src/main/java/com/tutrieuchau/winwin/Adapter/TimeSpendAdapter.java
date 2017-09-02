package com.tutrieuchau.winwin.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tutrieuchau.winwin.Activity.TimeActivity;
import com.tutrieuchau.winwin.MainActivity;
import com.tutrieuchau.winwin.Model.TimeSpend;
import com.tutrieuchau.winwin.R;

import java.util.ArrayList;

/**
 * Created by ductam on 2017/08/30.
 */

public class TimeSpendAdapter extends ArrayAdapter<TimeSpend> implements View.OnClickListener {
    private ArrayList<TimeSpend> dataSet;
    private Context context;
    public TimeSpendAdapter( Context context,ArrayList<TimeSpend> data){
        super(context, R.layout.time_row_item,data);
        this.context = context;
        this.dataSet = data;
    }

    @Override
    public void onClick(View v) {

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TimeSpend timeSpend = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.time_row_item, parent, false);
        }
        //tets

        ImageView thumbnails = (ImageView) convertView.findViewById(R.id.timeThumbnail);
        thumbnails.setImageResource(timeSpend.icon);
        thumbnails.setBackgroundTintList(ContextCompat.getColorStateList(context,timeSpend.color));

        TextView title = (TextView) convertView.findViewById(R.id.tvTimeTitle);
        title.setText(timeSpend.title);
        TextView time = (TextView)convertView.findViewById(R.id.tvTimeSpend);
        time.setText(minuteToText(timeSpend.spendTime));
        return convertView;
    }
    private String minuteToText(int minute){
        String result = "";
        if(minute/60 != 0){
            result += minute/60+"h";
        }
        if(minute%60 != 0){
            result+= minute%60+"m";
        }
        return result;
    }
}
