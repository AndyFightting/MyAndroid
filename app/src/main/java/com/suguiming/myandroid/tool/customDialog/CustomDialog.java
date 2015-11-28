package com.suguiming.myandroid.tool.customDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.ItemTapListener;

/**
 * Created by suguiming on 15/11/27.
 */
public class CustomDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private ItemTapListener itemTapListener;

    public CustomDialog(Context context, ItemTapListener itemTapListener){
        super(context);
        this.context = context;
        this.itemTapListener = itemTapListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        TextView outTv = (TextView)findViewById(R.id.out_tv);
        TextView closeTv = (TextView)findViewById(R.id.close_tv);

        outTv.setOnClickListener(this);
        closeTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.dismiss();
        if (itemTapListener != null){
            itemTapListener.itemTap(v);
        }
    }

}
