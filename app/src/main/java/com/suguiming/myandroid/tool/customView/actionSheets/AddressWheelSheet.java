package com.suguiming.myandroid.tool.customView.actionSheets;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.utils.DBManager;
import com.suguiming.myandroid.tool.wheel.OnWheelChangedListener;
import com.suguiming.myandroid.tool.wheel.OnWheelScrollListener;
import com.suguiming.myandroid.tool.wheel.WheelView;
import com.suguiming.myandroid.tool.wheel.adapters.ArrayWheelAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suguiming on 15/12/17.
 */

//用 Android wheel 做的地址选取器，用另一个地区数据库wheel.db
public class AddressWheelSheet extends BaseSheetActivity {

    public WheelView onePicker;
    public WheelView twoPicker;
    public WheelView threePicker;
    public List<WheelModel> oneList = new ArrayList<>();
    public List<WheelModel> twoList = new ArrayList<>();
    public List<WheelModel> threeList = new ArrayList<>();

    public int oneIndex;
    public int twoIndex;
    public int threeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_address_wheel_sheet);
        TextView titleTv = (TextView) findViewById(R.id.title_tv);
        titleTv.setText("选择地区");

        onePicker = (WheelView) findViewById(R.id.one_pk);
        twoPicker = (WheelView) findViewById(R.id.two_pk);
        threePicker = (WheelView) findViewById(R.id.three_pk);

        oneList.addAll(getList(this, "province", 0));
        String[] nameArray = new String[oneList.size()];
        for (int i = 0; i < oneList.size(); i++) {
            nameArray[i] = oneList.get(i).getRegionName();
        }
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<>(this, nameArray);
        onePicker.setViewAdapter(adapter);

        onePicker.setVisibleItems(5);
        onePicker.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                oneIndex = newValue;
            }
        });
        onePicker.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                setTwoPicker();
                setThreePicker();
            }
        });

        setTwoPicker();
        setThreePicker();
    }

    private void setTwoPicker() {
        twoList.clear();
        twoList.addAll(getList(this, "city", oneList.get(oneIndex).getRegionId()));

        String[] nameArray = new String[twoList.size()];
        for (int i = 0; i < twoList.size(); i++) {
            nameArray[i] = twoList.get(i).getRegionName();
        }
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<>(this, nameArray);

        twoPicker.setViewAdapter(adapter);
        twoPicker.setVisibleItems(5);
        twoPicker.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                twoIndex = newValue;
            }
        });
        twoPicker.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                setThreePicker();
            }
        });

        twoIndex = 0;
        twoPicker.setCurrentItem(twoIndex);
    }

    private void setThreePicker() {
        threeList.clear();
        threeList.addAll(getList(this, "zone", twoList.get(twoIndex).getRegionId()));
        String[] nameArray = new String[threeList.size()];
        for (int i = 0; i < threeList.size(); i++) {
            nameArray[i] = threeList.get(i).getRegionName();
        }
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<>(this, nameArray);

        threePicker.setViewAdapter(adapter);
        threePicker.setVisibleItems(5);
        threePicker.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                threeIndex = newValue;
            }
        });

        threeIndex = 0;
        threePicker.setCurrentItem(threeIndex);
    }

    public void itemTap(View view) {
        if (itemTapListener != null) {
            switch (view.getId()) {
                case R.id.yes://确定,通过tag传递值
                    Map<String, String> data = new HashMap<>();
                    data.put("one", oneList.get(oneIndex).getRegionName());
                    data.put("two", twoList.get(twoIndex).getRegionName());

                    if (threeList.size()>0){
                        data.put("three", threeList.get(threeIndex).getRegionName());
                    }else {
                        data.put("three", "");
                    }
                    view.setTag(data);
                    break;
                default:
                    break;
            }
            itemTapListener.itemTap(view);
        }
        dismiss();
    }

    //因为数据库放三个表...province, city, zone
    public ArrayList<WheelModel> getList(Context context, String tableName, int parentId) {
        try {
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(DBManager.getDbPath(context, DBManager.WHEEL_NAME), null);
            Cursor cursor = database.rawQuery("SELECT region_id ,parent_id, region_name FROM " + tableName + " where parent_id = " + parentId, null);

            if (cursor != null) {
                int num = cursor.getCount();
                ArrayList<WheelModel> addressList = new ArrayList<>(num);
                while (cursor.moveToNext()) {
                    int region_id = cursor.getInt(cursor.getColumnIndex("region_id"));
                    int parent_id = cursor.getInt(cursor.getColumnIndex("parent_id"));
                    String region_name = cursor.getString(cursor.getColumnIndex("region_name"));

                    WheelModel model = new WheelModel();
                    model.setParentId(parent_id);
                    model.setRegionId(region_id);
                    model.setRegionName(region_name);
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

    class WheelModel {
        public int regionId;
        public int parentId;
        public String regionName;

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

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
