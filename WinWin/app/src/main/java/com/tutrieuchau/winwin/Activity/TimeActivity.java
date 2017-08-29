package com.tutrieuchau.winwin.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tutrieuchau.winwin.R;

import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends AppCompatActivity {
    private  PieChart pieChart;
    private RelativeLayout mainPieChartLayout;
    private float[] ydata = {5,10,15,30,40};
    private String[] xdata = {"Android Studio","Xcode","Visual Studio","Notepad++","Edit Text"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        mainPieChartLayout = (RelativeLayout) findViewById(R.id.mainPieChart);
        pieChart = new PieChart(this);
        mainPieChartLayout.addView(pieChart);
        mainPieChartLayout.setBackgroundColor(Color.LTGRAY);

        pieChart.setUsePercentValues(true);
        Description description = new Description();
        description.setText("Code Editor");
        pieChart.setDescription(description);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(7);
        pieChart.setTransparentCircleRadius(10);

        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });
        //add data
        addDataToPieChart();

        //
        Legend le = pieChart.getLegend();
        le.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        le.setXEntrySpace(7);
        le.setYEntrySpace(5);



    }
    private void addDataToPieChart(){
        List<PieEntry> yVals = new ArrayList<>();
        for (int i = 0; i < ydata.length ; i ++){
            yVals.add(new PieEntry(ydata[i],i));

        }
        ArrayList<String> xVals = new ArrayList<>();

        for(int i = 0; i < xdata.length ; i++){
            xVals.add(xdata[i]);

        }
        PieDataSet pieDataSet = new PieDataSet(yVals,"Code edit");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(c);
        }

        for (int c: ColorTemplate.JOYFUL_COLORS){
            colors.add(c);
        }

        for (int c: ColorTemplate.COLORFUL_COLORS){
            colors.add(c);
        }

        for (int c: ColorTemplate.LIBERTY_COLORS){
            colors.add(c);
        }

        for (int c: ColorTemplate.PASTEL_COLORS){
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());

        pieDataSet.setColors(colors);

        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        pieChart.setData(data);

        pieChart.highlightValue(null);

        pieChart.invalidate();


    }
}
