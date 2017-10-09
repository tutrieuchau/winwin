package com.tutrieuchau.winwin.Support;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.tutrieuchau.winwin.Adapter.SelectIconAdapter;
import com.tutrieuchau.winwin.R;
import com.tutrieuchau.winwin.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by ductam on 2017/10/06.
 */

public class SelectIconDialog extends Dialog {
    private Context context;
    private ListView listView;
    private SelectIconDialogCallBack callBack;
    public SelectIconDialog(Context context,SelectIconDialogCallBack callBack){
        super(context);
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.select_icon_dialog);
        int height = (context.getResources().getDisplayMetrics().heightPixels);
        getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,(int)(height*0.75));
        ImageView btnClose = (ImageView) findViewById(R.id.selectIconClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        listView = (ListView) findViewById(R.id.selectIonList);
        ArrayList<Integer> rdata = new ArrayList<>();
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        for (int i =0 ;i < Utils.LIST_COLOR_ICON.length ; i++){
            if(rdata == null){
                rdata = new ArrayList<>();
            }
            if(rdata.size() == 5){
                data.add(rdata);
                rdata = null;
            }else{
                rdata.add(Utils.LIST_COLOR_ICON[i]);
            }
        }
        SelectIconAdapter adapter = new SelectIconAdapter(context,data,callBack);
        listView.setAdapter(adapter);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.setCanceledOnTouchOutside(false);
    }
    public interface SelectIconDialogCallBack{
        void onSelected(int icon);
    }
}
