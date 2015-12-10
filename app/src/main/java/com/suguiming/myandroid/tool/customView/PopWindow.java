package com.suguiming.myandroid.tool.customView;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.utils.ItemTapListener;

/**
 * Created by suguiming on 15/11/28.
 */
public class PopWindow{

    public static PopupWindow pop;
    public static ItemTapListener itemTapListener;
    public static Activity mActivity;

    public static void show(Activity activity,View anchorView,ItemTapListener listener) {
        itemTapListener = listener;
        mActivity = activity;

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemTap(v);
            }
        };

        LayoutInflater inflater = LayoutInflater.from(activity);
        View popView = inflater.inflate(R.layout.pop_window, null);

        Button btA = (Button)popView.findViewById(R.id.button_a);
        Button btB = (Button)popView.findViewById(R.id.button_b);
        btA.setOnClickListener(clickListener);
        btB.setOnClickListener(clickListener);

        pop = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable(0xffffff));
        pop.setFocusable(true);
        pop.showAsDropDown(anchorView);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss();
            }
        });

        WindowManager.LayoutParams attributes=activity.getWindow().getAttributes();
        attributes.alpha = 0.7f;
        activity.getWindow().setAttributes(attributes);

    }
    public static void itemTap(View view){
        dismiss();
        if (itemTapListener != null){
            itemTapListener.itemTap(view);
        }
    }

    public static void dismiss(){
        pop.dismiss();
        WindowManager.LayoutParams attributes=mActivity.getWindow().getAttributes();
        attributes.alpha = 1.0f;
        mActivity.getWindow().setAttributes(attributes);
    }
}
