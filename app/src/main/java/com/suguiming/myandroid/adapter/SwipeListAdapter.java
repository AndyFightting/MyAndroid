package com.suguiming.myandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.suguiming.myandroid.R;

/**
 * Created by suguiming on 15/11/25.
 */
public class SwipeListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private int num;
    private InnerItemTapListener innerItemTapListener;


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
            viewHolder.titleTv = (TextView)convertView.findViewById(R.id.titleTv);
            viewHolder.deleteTv = (TextView)convertView.findViewById(R.id.deleteTv);
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.titleTv.setText("向左滑动  "+position);
        viewHolder.deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (innerItemTapListener != null){
                    innerItemTapListener.innerItemTap(v,position);
                }
            }
        });

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
        TextView titleTv;
        TextView deleteTv;

    }

    public void setNum(int num){
        this.num = num;
    }

    public interface InnerItemTapListener{
        void innerItemTap(View view,int position);
    }
    public void setInnerItemTapListener(InnerItemTapListener innerItemTapListener) {
        this.innerItemTapListener = innerItemTapListener;
    }


}
