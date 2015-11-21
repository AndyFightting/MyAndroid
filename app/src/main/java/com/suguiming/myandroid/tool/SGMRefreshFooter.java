package com.suguiming.myandroid.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/20.
 */
public class SGMRefreshFooter extends RelativeLayout {

    private Context mContext;
    private TextView titleTv;

    public SGMRefreshFooter(Context context){
        super(context);
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.refresh_footer, this);
        titleTv = (TextView) view.findViewById(R.id.title);
    }
}
