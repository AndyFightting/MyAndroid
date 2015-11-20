package com.suguiming.myandroid.tab2;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suguiming.myandroid.MainActivity;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.StudentAdapter;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.bean.Student;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.customRefreshHeader.SGMRefreshHeader;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.ImageTask;
import in.srain.cube.image.iface.ImageLoadHandler;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment2 extends BaseFragment {

    private ListView studentListView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();
    private PtrClassicFrameLayout mPtrFrame;//刷新框架

    private int listTmpNum = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        //------这里面初始化fragment view----------
        setMainView(R.layout.fragment2_layout);
        showTitleView("学员列表");

        makeFakeList(listTmpNum);

        studentListView = (ListView)mainView.findViewById(R.id.student_list);
        studentAdapter = new StudentAdapter(mainActivity,studentList,fatherView);
        studentListView.setAdapter(studentAdapter);
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("item 点击" + position);
            }
        });
        initRefreshView();
        return view;
    }

    //构造假数据
    private void makeFakeList(int num){
        for (int i=0;i<num;i++){
            studentList.add(new Student());
        }

    }

    //下拉刷新frame----- https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh
    private void initRefreshView(){
        mPtrFrame = (PtrClassicFrameLayout) mainView.findViewById(R.id.rotate_header_list_view_frame);

        //方案一:用框架自带的刷新
        mPtrFrame.setLastUpdateTimeRelateObject(this);//显示距离上次刷新时间

        //方案二:用自定义刷新头部--------
//        SGMRefreshHeader  header = new SGMRefreshHeader(mainActivity);
//        mPtrFrame.setHeaderView(header);
//        mPtrFrame.addPtrUIHandler(header);
        //----------方案二end----------

        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //开始刷新 ------
                beginRefresh();
                //demo 1.8秒后结束刷新----实际项目在请求成功或失败后调用 mPtrFrame.refreshComplete();
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                    }
                }, 1800);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    public void tabTap(){
        mPtrFrame.autoRefresh();
    }

    private void beginRefresh(){
         makeFakeList(listTmpNum);
         studentAdapter.notifyDataSetChanged();
    }

}
