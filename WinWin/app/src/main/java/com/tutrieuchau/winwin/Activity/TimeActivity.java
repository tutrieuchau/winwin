package com.tutrieuchau.winwin.Activity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
import com.tutrieuchau.winwin.Adapter.TimeSpendAdapter;
import com.tutrieuchau.winwin.Model.TimeSpend;
import com.tutrieuchau.winwin.R;

import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends AppCompatActivity implements View.OnClickListener{
    private  PieChart pieChart;
    private RelativeLayout mainPieChartLayout;
    private float[] ydata = {5,10,15,30,40};
    private String[] xdata = {"Android Studio","Xcode","Visual Studio","Notepad++","Edit Text"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        // Time Spent List View
        ArrayList<TimeSpend> timeSpends = new ArrayList<>();
        TimeSpend timeSpend = new TimeSpend("Sleep",R.color.antiquewhite,70,R.drawable.ic_time_eat);
        timeSpends.add(timeSpend);
        TimeSpend timeSpend1 = new TimeSpend("Work",R.color.bisque,50,R.drawable.ic_time_eat);
        timeSpends.add(timeSpend1);
        TimeSpend timeSpend2 = new TimeSpend("Learning English",R.color.tomato,20,R.drawable.ic_time_eat);
        timeSpends.add(timeSpend2);
        timeSpends.add(timeSpend1);
        timeSpends.add(timeSpend);

        final TimeSpendAdapter timeSpendAdapter = new TimeSpendAdapter(this,timeSpends);
        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.timeListView);
        listView.setAdapter(timeSpendAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(getResources().getColor(R.color.goldenrod)));
                editItem.setIcon(R.drawable.ic_time_edit);
                // set item width
                editItem.setWidth(80);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(80);

                // set a icon
                deleteItem.setIcon(R.drawable.ic_time_trash);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        timeSpendAdapter.clear();
                        break;
                    case 1:
                        break;
                }
                return  false;
            }
        });
        // button add
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        // Pie charts setting
        mainPieChartLayout = (RelativeLayout) findViewById(R.id.mainPieChart);
        pieChart = (PieChart) findViewById(R.id.timePieChart);
        int scrHeight = Resources.getSystem().getDisplayMetrics().widthPixels-200;
        pieChart.setMinimumHeight(scrHeight);
        pieChart.setMinimumWidth(scrHeight);


        pieChart.setUsePercentValues(true);
        Description description = new Description();
        description.setText("");
        pieChart.setDescription(description);
        // center hole setting
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(20);
        pieChart.setTransparentCircleRadius(30);// border size

        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);// Router

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
        PieDataSet pieDataSet = new PieDataSet(yVals,"");
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

    @Override
    public void onClick(View v) {
        //TODO:Add new Diablog
    }
}
