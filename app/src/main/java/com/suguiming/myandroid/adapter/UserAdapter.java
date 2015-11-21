package com.suguiming.myandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.bean.User;

import java.util.List;

/**
 * Created by suguiming on 15/11/21.
 */
public class UserAdapter extends ArrayAdapter<User> {

    private int layoutId;
    public UserAdapter(Context context, int resourceId, List<User> objects){
          super(context,resourceId,objects);
          layoutId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User user = getItem(position);
        ViewHolder viewHolder;
        View layoutView;

        if (convertView == null){
            layoutView = LayoutInflater.from(getContext()).inflate(layoutId,null);
            viewHolder = new ViewHolder();
            viewHolder.headImage = (ImageView)layoutView.findViewById(R.id.head_img);
            viewHolder.nameTv = (TextView)layoutView.findViewById(R.id.name_tv);
            layoutView.setTag(viewHolder);
        }else {
           layoutView = convertView;
           viewHolder = (ViewHolder)layoutView.getTag();
        }
        //--------在下面赋值 ----------------
        viewHolder.headImage.setImageResource(user.getHeadImgId());
        viewHolder.nameTv.setText(user.getName());

        return layoutView;
    }

    class ViewHolder{
        ImageView headImage;
        TextView nameTv;
    }
}
