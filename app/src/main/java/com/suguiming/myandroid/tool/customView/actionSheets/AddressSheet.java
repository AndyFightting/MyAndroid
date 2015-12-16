package com.suguiming.myandroid.tool.customView.actionSheets;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.utils.DBManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dongxuewei on 15/1/15.
 */
public class AddressSheet extends BaseSheetActivity {

    public CustomNumberPicker onePicker;
    public CustomNumberPicker twoPicker;
    public CustomNumberPicker threePicker;
    public List<AddressModel> oneList = new ArrayList<>();
    public List<AddressModel> twoList = new ArrayList<>();
    public List<AddressModel> threeList = new ArrayList<>();
    public String[] oneValues;
    public String[] twoValues;
    public String[] threeValues;
    public int oneIndex;
    public int twoIndex;
    public int threeIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_address_sheet);
        TextView titleTv = (TextView) findViewById(R.id.title_tv);
        titleTv.setText("选择地区");

        onePicker = (CustomNumberPicker) findViewById(R.id.one_pk);
        twoPicker = (CustomNumberPicker) findViewById(R.id.two_pk);
        threePicker = (CustomNumberPicker) findViewById(R.id.three_pk);

        oneList.addAll(getList(this, 1));
        oneValues = new String[oneList.size()];
        for (int i = 0; i < oneList.size(); i++) {
            oneValues[i] = oneList.get(i).getRegionName();
        }
        onePicker.setMinValue(0);
        onePicker.setMaxValue(oneList.size() - 1);
        onePicker.setDisplayedValues(oneValues);
        onePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                oneIndex = newVal;
                setTwoPicker();
                setThreePicker();
            }
        });
        setTwoPicker();
        setThreePicker();
    }

    private void setTwoPicker() {
        twoList.clear();
        twoList.addAll(getList(this, oneList.get(oneIndex).getRegionId()));

        twoValues = new String[twoList.size()];
        for (int i = 0; i < twoList.size(); i++) {
            twoValues[i] = twoList.get(i).getRegionName();
        }

        // https://code.google.com/p/android/issues/detail?id=181591
        twoPicker.setDisplayedValues(null);//关键代码！要手动清除displayValue...不然会崩！！fuck !弄了一天！草啊！！
        twoPicker.setMinValue(0);
        twoPicker.setMaxValue(twoValues.length - 1);
        twoPicker.setDisplayedValues(twoValues);
        twoPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                twoIndex = newVal;
                setThreePicker();
            }
        });

        twoIndex = 0;
        twoPicker.setValue(twoIndex);
    }

    private void setThreePicker() {
        threeList.clear();
        threeList.addAll(getList(this, twoList.get(twoIndex).getRegionId()));

        threeValues = new String[threeList.size()];
        for (int i = 0; i < threeList.size(); i++) {
            threeValues[i] = threeList.get(i).getRegionName();
        }

        threePicker.setDisplayedValues(null);
        threePicker.setMinValue(0);
        threePicker.setMaxValue(threeValues.length - 1);
        threePicker.setDisplayedValues(threeValues);
        threePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                threeIndex = newVal;
            }
        });

        threeIndex = 0;
        threePicker.setValue(threeIndex);
    }

    public void itemTap(View view) {
        if (itemTapListener != null) {
            switch (view.getId()) {
                case R.id.yes://确定,通过tag传递值
                    Map<String, String> data = new HashMap<>();
                    data.put("one", oneList.get(oneIndex).getRegionName());
                    data.put("two", twoList.get(twoIndex).getRegionName());
                    data.put("three", threeList.get(threeIndex).getRegionName());
                    view.setTag(data);
                    break;
                default:
                    break;
            }
            itemTapListener.itemTap(view);
        }
        dismiss();
    }

    public ArrayList<AddressModel> getList(Context context, int parentId) {
        try {
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DBManager.getDbPath(context, DBManager.ADDRESS_NAME), null);
            Cursor cursor = database.rawQuery("SELECT region_id, region_name FROM region where parent_id = " + parentId, null);

            if (cursor != null) {
                int num = cursor.getCount();
                ArrayList<AddressModel> addressList = new ArrayList<>(num);
                while (cursor.moveToNext()) {
                    int regionId = cursor.getInt(cursor.getColumnIndex("region_id"));
                    String regionName = cursor.getString(cursor.getColumnIndex("region_name"));

                    AddressModel model = new AddressModel();
                    model.setRegionId(regionId);
                    model.setRegionName(regionName);
                    addressList.add(model);
                }
                cursor.close();
                database.close();
                return addressList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    class AddressModel {

        public int regionId;
        public String regionName;

        public int getRegionId() {
            return regionId;
        }

        public void setRegionId(int regionId) {
            this.regionId = regionId;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }
    }

}
