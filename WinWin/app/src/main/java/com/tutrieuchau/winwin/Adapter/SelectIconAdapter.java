package com.tutrieuchau.winwin.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Support.SelectIconDialog;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by ductam on 2017/10/09.
 */

public class SelectIconAdapter extends ArrayAdapter<ArrayList<Integer>>{
    private Context context;
    private ImageView first;
    private ImageView second;
    private ImageView third;
    private ImageView four;
    private ImageView five;
    int position;
    SelectIconDialog.SelectIconDialogCallBack callBack;

    public SelectIconAdapter(Context context, ArrayList<ArrayList<Integer>> data, SelectIconDialog.SelectIconDialogCallBack callBack){
        super(context, R.layout.select_icon_row_item,data);
        this.context = context;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ArrayList resourceArray = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.select_icon_row_item, parent, false);
        }
        this.position = position;
        first = (ImageView)convertView.findViewById(R.id.selectIconFirst);
        first.setImageResource((int)resourceArray.get(0));
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSelected((int)resourceArray.get(0));
            }
        });

        second = (ImageView)convertView.findViewById(R.id.selectIconSecond);
        second.setImageResource((int)resourceArray.get(1));
        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSelected((int)resourceArray.get(1));
            }
        });

        third = (ImageView)convertView.findViewById(R.id.selectIconThird);
        third.setImageResource((int)resourceArray.get(2));
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSelected((int)resourceArray.get(2));
            }
        });

        four = (ImageView)convertView.findViewById(R.id.selectIconFour);
        four.setImageResource((int)resourceArray.get(3));
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSelected((int)resourceArray.get(3));
            }
        });

        five = (ImageView)convertView.findViewById(R.id.selectIconFive);
        five.setImageResource((int)resourceArray.get(4));
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onSelected((int)resourceArray.get(4));
            }
        });
        return convertView;
    }

}
