package com.tutrieuchau.winwin.Support;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tutrieuchau.winwin.R;

/**
 * Created by ductam on 2017/09/26.
 */

public class HorizontalSingleBarChart extends RelativeLayout {
    private View rootView;
    private TextView tvTitle;
    private TextView tvPercent;
    private ImageView chartBackground;
    private ImageView chartProgress;
    public HorizontalSingleBarChart(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalSingleBarChart);
        String title = typedArray.getString(R.styleable.HorizontalSingleBarChart_title);
        int percent = typedArray.getInteger(R.styleable.HorizontalSingleBarChart_percent,0);
        int color = typedArray.getInteger(R.styleable.HorizontalSingleBarChart_color,context.getResources().getColor(R.color.themeLight));
        int tintColor = typedArray.getInteger(R.styleable.HorizontalSingleBarChart_tintColor,context.getResources().getColor(R.color.colorShadow));
        typedArray.recycle();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.horizontal_single_bar_chart,this,true);
        tvTitle = (TextView) rootView.findViewById(R.id.horizontalSingleBarChartTitle);
        tvPercent = (TextView) rootView.findViewById(R.id.horizontalSingleBarChartPercent);
        chartBackground = (ImageView)rootView.findViewById(R.id.horizontalSingleBarChartBackground);
        chartProgress = (ImageView)rootView.findViewById(R.id.horizontalSingleBarChartProgress);
        //
        tvTitle.setText(title);
        tvPercent.setText(percent+"%");
        chartBackground.setBackgroundColor(tintColor);
        chartProgress.setBackgroundColor(color);

    }
    public void setProgress(int progress){
        updateProgress(progress);
    }
    public void setColor(int color){

    }
    public void setTintColor(int color){

    }
    private void updateProgress(int percent){
        int progressWidth = (int)((float)percent/100*chartBackground.getWidth());
        ViewGroup.LayoutParams params = chartProgress.getLayoutParams();
        params.width = progressWidth;
        chartProgress.setLayoutParams(params);
        //
        tvPercent.setText(percent+"%");
    }
}
