package com.suguiming.myandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/25.
 */
public class SwipeListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int num;

    public SwipeListAdapter(Context context,int num){
        this.num = num;
        mContext = context;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return num;
    }

    @Override
    public View getView (final int position, View convertView, ViewGroup parent){
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_swipe,null);
            viewHolder = new ViewHolder();
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    class ViewHolder{

    }



}
