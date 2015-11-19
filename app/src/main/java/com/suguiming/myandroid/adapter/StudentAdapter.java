package com.suguiming.myandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.bean.Student;

import java.util.List;

/**
 * Created by suguiming on 15/11/19.
 */
public class StudentAdapter extends BaseAdapter{

    private Context mContext;
    private List<Student> studentList;
    private View fatherView;
    private LayoutInflater mInflater;

    public StudentAdapter(Context context,List<Student> students,View fatherView){
        mContext = context;
        studentList = students;
        this.fatherView = fatherView;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return studentList.size();
    }

    @Override
    public View getView (final int position, View convertView, ViewGroup parent){
         ViewHolder viewHolder;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item_student,null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
            //------设置点击事件，item里面控件 的点击都放adapter里处理--------------------
            viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    headImageTap(position);
                }
            });

           }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        //-------------设置值 holder -----------------------------------------------
        viewHolder.name.setText("hello "+position);
        viewHolder.imageView.setImageResource(R.mipmap.tab2s);

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    class ViewHolder{
        TextView name;
        ImageView imageView;
    }

//--------------------------------------------------------------------------------------------------
    private void headImageTap(int position){
       Toast.makeText(mContext,"图片点击"+position,Toast.LENGTH_SHORT).show();
    }

}
