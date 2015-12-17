package com.suguiming.myandroid.tool.customView.actionSheets;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.wheel.adapters.WheelViewAdapter;

import java.util.List;

public class AddressWheelAdapter implements WheelViewAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<AddressWheelSheet.WheelModel> lists;

    public AddressWheelAdapter(Context context, List<AddressWheelSheet.WheelModel> models) {
        lists = null;
        lists = models;
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemsCount() {
        return lists.size();
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        AddressWheelSheet.WheelModel model = lists.get(index);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_address_wheel, null);
            viewHolder = new ViewHolder();
            viewHolder.titleTv = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titleTv.setText(model.getRegionName());

        return convertView;
    }

    @Override
    public View getEmptyItem(View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    class ViewHolder {
        TextView titleTv;
    }
}
