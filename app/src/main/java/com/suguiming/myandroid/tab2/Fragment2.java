package com.suguiming.myandroid.tab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.StudentAdapter;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment2 extends BaseFragment {

    private ListView studentListView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        //------这里面初始化fragment view----------
        setMainView(R.layout.fragment2_layout);
        showTitleView("fragment 2");

        makeFakeList(10);

        studentListView = (ListView)mainView.findViewById(R.id.student_list);
        studentAdapter = new StudentAdapter(mainActivity,studentList,fatherView);
        studentListView.setAdapter(studentAdapter);

        return view;
    }

    //构造假数据
    private void makeFakeList(int num){
        for (int i=0;i<num;i++){
            studentList.add(new Student());
        }

    }

}
