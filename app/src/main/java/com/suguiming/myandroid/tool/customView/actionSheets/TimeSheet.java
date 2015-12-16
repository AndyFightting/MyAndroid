package com.suguiming.myandroid.tool.customView.actionSheets;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suguiming on 15/12/14.
 */
public class TimeSheet extends BaseSheetActivity {

    public static CustomNumberPicker hourPicker;
    public static CustomNumberPicker minutePicker;

    public static int hour;
    public static int minute;

    public static boolean handSet = false;//是否手动设置时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_time_sheet);
        TextView titleTv = (TextView)findViewById(R.id.title_tv);
        titleTv.setText("选择时间");

        if (!handSet){
            hour = DateUtil.getCurrentHour();
            minute = DateUtil.getCurrentMinute();
        }

        initHourPicker();
        initMinutePicker();

        hourPicker.setValue(hour);
        minutePicker.setValue(minute);
    }

    private void initHourPicker() {
        hourPicker = (CustomNumberPicker) findViewById(R.id.hour_pk);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23);

        hourPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return DateUtil.getNumStrig(value);
            }
        });

        hourPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                hour = newVal;
            }
        });
    }

    private void initMinutePicker() {
        minutePicker = (CustomNumberPicker) findViewById(R.id.minute_pk);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);

        minutePicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return DateUtil.getNumStrig(value);
            }
        });

        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                minute = newVal;
            }
        });
    }

    public static void setTime(int h, int m) {
        if (h < 0){
            h = 0;
        }else if (h > 23){
            h = 23;
        }

        if (m < 0 ){
            m = 0;
        }else if (m > 59){
            m = 59;
        }

        handSet = true;
        hour = h;
        minute = m;

        if (hourPicker != null){
            hourPicker.setValue(hour);
            minutePicker.setValue(minute);
        }
    }

    public void itemTap(View view) {
        if (itemTapListener != null) {
            switch (view.getId()) {
                case R.id.yes://确定,通过tag传递值
                    Map<String, String> data = new HashMap<>();
                    data.put("hour", DateUtil.getNumStrig(hour));
                    data.put("minute", DateUtil.getNumStrig(minute));
                    view.setTag(data);
                    break;
                default:
                    break;
            }
            itemTapListener.itemTap(view);
        }
        dismiss();
    }

    @Override
    protected void onDestroy() {
        hourPicker = null;
        minutePicker = null;

        super.onDestroy();
    }
}
