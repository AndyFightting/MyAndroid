package com.suguiming.myandroid.tool.customView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/28.
 */
public class HUD {
    public static Dialog hudDialog;
    public static TextView titleTv;

    public static void show(Context context, final String message){
        hudDialog = new Dialog(context, R.style.hudTheme) {
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.hud);
                titleTv = (TextView) findViewById(R.id.title_tv);
                titleTv.setText(message);
            }
        };
        hudDialog.setCancelable(true);
        hudDialog.setCanceledOnTouchOutside(false);
        hudDialog.show();
    }

    public static void dismiss(){
        if (hudDialog != null){
            if (hudDialog.isShowing()){

                //延迟0.7秒消失
                titleTv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hudDialog.dismiss();
                        hudDialog = null;
                    }
                }, 700);
            }
        }
    }

    public static void setMessage(String message){
        if (titleTv != null){
            titleTv.setText(message);
        }
    }
}
