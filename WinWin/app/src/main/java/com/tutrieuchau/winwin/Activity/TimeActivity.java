package com.tutrieuchau.winwin.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
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
import com.tutrieuchau.winwin.Service.SharePreferencesService;
import com.tutrieuchau.winwin.Support.AddTimeDialog;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TimeActivity extends BaseActivity implements View.OnClickListener{
    private  PieChart pieChart;
    private float[] ydata = {5,10,15,30,40};
    private String[] xdata = {"Android Studio","Xcode","Visual Studio","Notepad++","Edit Text"};
    private Context context;
    private ArrayList<TimeSpend> timeSpends;
    private TimeSpendAdapter timeSpendAdapter;
    private SharePreferencesService sharePreferencesService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        this.context = getApplicationContext();
        sharePreferencesService = new SharePreferencesService(this);
        // Time Spent List View
        timeSpends = sharePreferencesService.getSpendTimeList();
        timeSpendAdapter = new TimeSpendAdapter(this,timeSpends);
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
                editItem.setWidth(100);
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
                deleteItem.setWidth(100);

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
                        //on Edit
                        showEditDialog(position);
                        break;
                    case 1:
                        // on Delete
                        sharePreferencesService.removeItemInSpendTimeList(position);
                        timeSpends.remove(position);
                        timeSpendAdapter.notifyDataSetChanged();
                        addDataToPieChart();
                        break;
                }
                return  false;
            }
        });
        // button add
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        // Pie charts setting
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
        pieChart.setRotationEnabled(false);// Router

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
        ArrayList<Integer> colors = new ArrayList<>();
        float freePercent = 100;
        for(int i = 0;i<timeSpends.size(); i ++){
            float currentPercent = round(((float) timeSpends.get(i).spendTime/24/60)*100,2);
            freePercent = freePercent - currentPercent;
            yVals.add(new PieEntry(currentPercent,timeSpends.get(i).title));
            colors.add(context.getResources().getColor(timeSpends.get(i).color));
        }
        yVals.add(new PieEntry(freePercent,"Free Time"));
        colors.add(context.getResources().getColor(R.color.themeOff));
        PieDataSet pieDataSet = new PieDataSet(yVals,"");
        pieDataSet.setSliceSpace(3);
        pieDataSet.setSelectionShift(5);

//        colors.add(ColorTemplate.getHoloBlue());

        pieDataSet.setColors(colors);

        PieData data = new PieData(pieDataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        pieChart.setData(data);

        pieChart.highlightValue(null);

        pieChart.invalidate();

    }
    public static float round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdd){
            showAddDialog();
        }
    }
    private void showAddDialog(){
        final AddTimeDialog dialog = new AddTimeDialog(this,null);
        dialog.setDialogResult(new AddTimeDialog.OnAddTimeDialogResult() {
            @Override
            public void finish(TimeSpend timeSpend) {
                //TODO:Add New
                Toast.makeText(context,context.getString(R.string.time_add_dialog_add_success),Toast.LENGTH_LONG).show();
                timeSpends.add(timeSpend);
                timeSpendAdapter.notifyDataSetChanged();
                sharePreferencesService.addItemToSpendTimeList(timeSpend);
                // update chart data
                addDataToPieChart();
            }
        });
        dialog.show();
    }
    private void showEditDialog(final int position){
        TimeSpend timeSpend = this.timeSpends.get(position);
        final AddTimeDialog dialog = new AddTimeDialog(this,timeSpend);
        dialog.setDialogResult(new AddTimeDialog.OnAddTimeDialogResult() {
            @Override
            public void finish(TimeSpend timeSpend) {
                Toast.makeText(context,getString(R.string.time_add_dialog_edit_success),Toast.LENGTH_LONG).show();
                timeSpends.remove(position);
                timeSpends.add(position,timeSpend);
                timeSpendAdapter.notifyDataSetChanged();
                ///
                sharePreferencesService.updateSpendTimeList(position,timeSpend);
                addDataToPieChart();
            }
        });
        dialog.show();
    }
}
