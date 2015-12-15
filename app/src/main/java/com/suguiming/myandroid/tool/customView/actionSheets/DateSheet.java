package com.suguiming.myandroid.tool.customView.actionSheets;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.utils.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by suguiming on 15/12/15.
 */
public class DateSheet extends BaseSheetActivity {

    public static RelativeLayout yearLayout;
    public static CustomNumberPicker yearPicker;
    public static CustomNumberPicker monthPicker;
    public static CustomNumberPicker dayPicker;

    public static int year;
    public static int month; //月份数据是1到12
    public static int day;
    public static int dayNums;//该月有多少天

    public static boolean handSet = false;//是否手动设置日期
    public static boolean hideYearPicker = false;//不要选择年份

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_date_sheet);
        TextView titleTv = (TextView) findViewById(R.id.title_tv);
        titleTv.setText("选择日期");

        if (!handSet) {
            year = DateUtil.getCurrentYear();
            month = DateUtil.getCurrentMonth();
            day = DateUtil.getCurrentDay();
            setDayNums();
        }

        initYearPicker();
        initMonthPicker();
        initDayPicker();

        yearPicker.setValue(year);
        monthPicker.setValue(month);
        dayPicker.setValue(day);
    }

    private void initYearPicker() {
        yearLayout = (RelativeLayout) findViewById(R.id.year_layout);
        yearPicker = (CustomNumberPicker) findViewById(R.id.year_pk);
        yearPicker.setMinValue(year - 35);//前35年到后10年
        yearPicker.setMaxValue(year + 10);
        yearPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return value + "";
            }
        });

        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                year = newVal;
                setDayNums();
            }
        });

        if (hideYearPicker) {
            yearLayout.setVisibility(View.GONE);
        }
    }

    private void initMonthPicker() {
        monthPicker = (CustomNumberPicker) findViewById(R.id.month_pk);
        monthPicker.setMinValue(1);
        monthPicker.setMaxValue(12);
        monthPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return DateUtil.getNumStrig(value);
            }
        });

        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                month = newVal;
                setDayNums();
            }
        });
    }

    private void initDayPicker() {
        dayPicker = (CustomNumberPicker) findViewById(R.id.day_pk);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(dayNums);
        dayPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return DateUtil.getNumStrig(value);
            }
        });

        dayPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                day = newVal;
            }
        });
    }

    public static void setDayNums() {
        dayNums = DateUtil.getDayNums(year, month);
        if (day > dayNums) {
            day = dayNums;
        }
        if (dayPicker != null) {
            dayPicker.setMaxValue(dayNums);
        }
    }

    public static void setDate(int y, int m, int d) {
        if (m < 1) {
            m = 1;
        } else if (m > 12) {
            m = 12;
        }

        if (d < 1) {
            d = 1;
        }

        handSet = true;
        year = y;
        month = m;
        day = d;
        setDayNums();

        if (yearPicker != null) {
            yearPicker.setValue(year);
            monthPicker.setValue(month);
            dayPicker.setValue(day);
        }
    }

    public static void hideYearPicker() {
        hideYearPicker = true;
        if (yearLayout != null) {
            yearLayout.setVisibility(View.GONE);
        }
    }

    public void itemTap(View view) {
        if (itemTapListener != null) {
            switch (view.getId()) {
                case R.id.yes://确定,通过tag传递值
                    Map<String, String> data = new HashMap<>();
                    data.put("year", year + "");
                    data.put("month", DateUtil.getNumStrig(month)); //1到12
                    data.put("day", DateUtil.getNumStrig(day));
                    view.setTag(data);
                    break;
                default:
                    break;
            }
            itemTapListener.itemTap(view);
        }
        dismiss();
    }
}
