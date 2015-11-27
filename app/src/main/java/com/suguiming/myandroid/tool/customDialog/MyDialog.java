package com.suguiming.myandroid.tool.customDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/27.
 */
public class MyDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private ItemTapListener itemTapListener;

    public MyDialog(Context context,ItemTapListener itemTapListener){
        super(context);
        this.context = context;
        this.itemTapListener = itemTapListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_my);

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

    public interface ItemTapListener{
      void itemTap(View view);
    }
}
