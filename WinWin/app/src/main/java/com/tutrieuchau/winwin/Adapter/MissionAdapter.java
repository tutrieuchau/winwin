package com.tutrieuchau.winwin.Adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.tutrieuchau.winwin.Model.Mission;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tutr on 9/25/2017.
 */

public class MissionAdapter extends ArrayAdapter<Mission> {
    Context context;
    public MissionAdapter(Context context, ArrayList<Mission> data){
        super(context, R.layout.fragment_mission_row_item,data);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Mission mission = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_mission_row_item, parent, false);
        }
        // thumbnail
        ImageView thumbnail = (ImageView)convertView.findViewById(R.id.missionThumbnail);
        thumbnail.setImageResource(Common.getThumbnailIcon(mission.thumbnail));
        Drawable drawable = context.getResources().getDrawable(R.drawable.btn_oval);
        drawable.setColorFilter(context.getResources().getColor(Common.getThumbnailColor(mission.thumbnail)), PorterDuff.Mode.SRC_IN);
        thumbnail.setBackgroundDrawable(drawable);
        // Title
        TextView title = (TextView)convertView.findViewById(R.id.missionTitle);
        title.setText(mission.title);
        // detail
        TextView detail = (TextView)convertView.findViewById(R.id.missionDetail);
        detail.setText(mission.detail);
        //Reward
        TextView reward = (TextView)convertView.findViewById(R.id.missionReward);
        reward.setText(mission.reward);
        //Alarm
        ImageView mon = (ImageView)convertView.findViewById(R.id.missionMon);
        ImageView tue = (ImageView)convertView.findViewById(R.id.missionTue);
        ImageView wed = (ImageView)convertView.findViewById(R.id.missionWed);
        ImageView thu = (ImageView)convertView.findViewById(R.id.missionThu);
        ImageView fri = (ImageView)convertView.findViewById(R.id.missionFri);
        ImageView sat = (ImageView)convertView.findViewById(R.id.missionSat);
        ImageView sun = (ImageView)convertView.findViewById(R.id.missionSun);
        List<Mission.Alarm> alarms = mission.alarms;
        if(!alarms.get(0).active){
            mon.setVisibility(View.GONE);
        }
        if(!alarms.get(1).active){
            tue.setVisibility(View.GONE);
        }
        if(!alarms.get(2).active){
            wed.setVisibility(View.GONE);
        }
        if(!alarms.get(3).active){
            thu.setVisibility(View.GONE);
        }
        if(!alarms.get(4).active){
            fri.setVisibility(View.GONE);
        }
        if(!alarms.get(5).active){
            sat.setVisibility(View.GONE);
        }
        if(!alarms.get(6).active){
            sun.setVisibility(View.GONE);
        }
        //Detail Progress
        HorizontalBarChart detailProgress = (HorizontalBarChart)convertView.findViewById(R.id.missionDetailProgress);
//        detailProgress.getXAxis().setEnabled(false);
//        detailProgress.getXAxis().setDrawLabels(false);
//        detailProgress.getAxisLeft().setEnabled(false);
//        detailProgress.getAxisLeft().setDrawLabels(false);
        final ArrayList<String> xVals = getXAxisValues(mission.taskProgress);
        XAxis aXis = detailProgress.getXAxis();
        aXis.setDrawGridLines(false);
        aXis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xVals.get(0);
            }
        });
        detailProgress.setData(getBarData(mission.taskProgress));
        detailProgress.animateXY(2000, 2000);
        detailProgress.invalidate();

        //Main progress
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int width = display.getWidth();
        int totalWidth = width - 20 - 20;

        float completePercent = getTotalCompletePercent(mission.taskProgress);
        int progressWidth = (int)(totalWidth*completePercent);
        RelativeLayout missionComplete = (RelativeLayout) convertView.findViewById(R.id.missionComplete);
        ViewGroup.LayoutParams params = missionComplete.getLayoutParams();
        params.width = progressWidth;
        missionComplete.setLayoutParams(params);

        TextView missionMainProgressPercent = (TextView) convertView.findViewById(R.id.missionCompleteText);
        missionMainProgressPercent.setText((int)(completePercent*100)+"%");

        return convertView;
    }
    private BarData getBarData(List<Mission.Progress> progresses) {
        float bandwidth =5f;
        float spaceForBar = 8f;
        ArrayList<BarEntry> barEntrys = new ArrayList<>();
        for(int i = 0;i<progresses.size() ; i++){
            Mission.Progress progress = progresses.get(i);
            float percent = (float) progress.progressTime/(float) progress.totalTime;
            barEntrys.add(new BarEntry(i*spaceForBar,percent));
        }
        BarDataSet barDataSet = new BarDataSet(barEntrys,"");
        barDataSet.setColor(context.getResources().getColor(R.color.themeLight));
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(bandwidth);

        return barData;
    }
    private ArrayList<String> getXAxisValues(List<Mission.Progress> progresses) {
        ArrayList<String> xAxis = new ArrayList<>();
        for (Mission.Progress progress: progresses
             ) {
            xAxis.add(progress.taskName);
        }
        return xAxis;
    }
    private float getTotalCompletePercent(List<Mission.Progress> progresses){
        float percent;
        int totalTime = 0;
        int progressTime = 0;
        for (Mission.Progress progress: progresses
                ) {
            totalTime = totalTime + progress.totalTime;
            progressTime = progressTime + progress.progressTime;
        }
        percent = (float) progressTime/(float) totalTime;
        return percent;
    }
}
