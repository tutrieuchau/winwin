package com.tutrieuchau.winwin.Support;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.tutrieuchau.winwin.R;

/**
 * Created by ductam on 2017/10/19.
 */

public class ConfirmDialog extends Dialog {
    private Context context;
    String title, message, cancelTitle, agreeTitle;
    private ConfirmDialogOnClickListener onClickListener;
    public ConfirmDialog(Context context){
        super(context);
        this.context = context;
    }
    public ConfirmDialog(Context context,String title, String message){
        super(context);
        this.context = context;
        this.title = title;
        this.message = message;
    }
    public ConfirmDialog(Context context,String title, String message,String canelTitle, String agreeTitle){
        super(context);
        this.context = context;
        this.title = title;
        this.message = message;
        this.cancelTitle = canelTitle;
        this.agreeTitle = agreeTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.confirm_dialog);
        TextView cancel = (TextView) findViewById(R.id.confirmDialogCancel);
        TextView agree = (TextView) findViewById(R.id.confirmDialogAgree);
        if(title != null){
            TextView titleTxt = (TextView) findViewById(R.id.confirmDialogTitle);
            titleTxt.setText(title);
        }
        if(message != null){
            TextView messageTxt = (TextView) findViewById(R.id.confirmDialogMessage);
            messageTxt.setText(message);
        }
        if(cancelTitle != null){
            cancel.setText(cancelTitle);
        }
        if(agreeTitle != null){
            agree.setText(agreeTitle);
        }
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null){
                    onClickListener.onClick(v.getId());
                }
                dismiss();
            }
        });
    }
    public interface ConfirmDialogOnClickListener{
        void onClick(int id);
    }
    public void setOnClickListener(ConfirmDialogOnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
}
