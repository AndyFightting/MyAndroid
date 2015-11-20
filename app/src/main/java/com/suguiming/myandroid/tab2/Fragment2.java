package com.suguiming.myandroid.tab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.adapter.StudentAdapter;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.bean.Student;
import com.suguiming.myandroid.tool.customRefresh.SGMRefreshHeader;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by suguiming on 15/11/18.
 */
public class Fragment2 extends BaseFragment {

    private ListView studentListView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();
    private PtrClassicFrameLayout mPtrFrame;//刷新框架

    private int listTmpNum = 9;
    private boolean canLoadMore = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        //------这里面初始化fragment view----------
        setMainView(R.layout.fragment2_layout);
        showTitleView("学员列表");

        addStudentToList(listTmpNum);

        studentListView = (ListView)mainView.findViewById(R.id.student_list);
        studentAdapter = new StudentAdapter(mainActivity,studentList,fatherView);
        studentListView.setAdapter(studentAdapter);
        studentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("item 点击" + position);
            }
        });

        addRefreshHeader();
        addRefreshFooter();

        return view;
    }

    private void addRefreshHeader(){
        mPtrFrame = (PtrClassicFrameLayout) mainView.findViewById(R.id.rotate_header_list_view_frame);
        //方案一:用框架自带的刷新
//        mPtrFrame.setLastUpdateTimeRelateObject(this);//显示距离上次刷新时间

        //方案二:用自定义刷新头部--------
        SGMRefreshHeader  header = new SGMRefreshHeader(mainActivity);
        mPtrFrame.setHeaderView(header);
        mPtrFrame.addPtrUIHandler(header);
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
                }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    public void addRefreshFooter(){
        studentListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:// 当不滚动时
                        if (studentListView.getLastVisiblePosition() == (studentListView.getCount() - 1)) {// 判断滚动到底部
                            if (canLoadMore) {    //加载更多
                                beginLoadMore();
                                showLoadMoreFooter("加载中...");
                            } else {               //已经没有更多
                                showLoadMoreFooter("数据全部加载完毕");
                            }
                            break;
                        }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public void tabTap(){
        mPtrFrame.autoRefresh();
    }

    private void beginRefresh(){
         listTmpNum = 9;
         canLoadMore = true;

         studentList.clear();
         addStudentToList(listTmpNum);
         studentAdapter.notifyDataSetChanged();
    }
    private void beginLoadMore(){
        listTmpNum +=3; //假设每次增加3条数据
        addStudentToList(3);
        studentAdapter.notifyDataSetChanged();

        if (listTmpNum >20){ //假设最多20条数据
            canLoadMore = false;
        }
    }

    //构造假数据
    private void addStudentToList(int num){
        for (int i=0;i<num;i++){
            studentList.add(new Student());
        }
    }

}
