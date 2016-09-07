package com.suguiming.myandroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.base.BaseFragment;
import com.suguiming.myandroid.tab0.Fragment0;
import com.suguiming.myandroid.tool.other.BackCycleService;
import com.suguiming.myandroid.tool.utils.DBManager;
import com.suguiming.myandroid.tool.utils.MainBroadcastReceiver;
import com.suguiming.myandroid.tool.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MainBroadcastReceiver mainBroadcastReceiver;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private List<ImageView> tabImgList =new ArrayList<>();

    public FragmentManager fragmentManager;
    public int selectedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();

        tabImgList.add((ImageView) findViewById(R.id.tab0_img));
        tabImgList.add((ImageView) findViewById(R.id.tab1_img));
        tabImgList.add((ImageView)findViewById(R.id.tab2_img));
        tabImgList.add((ImageView)findViewById(R.id.tab3_img));

        fragmentList.add((BaseFragment)fragmentManager.findFragmentById(R.id.fragment0));
        fragmentList.add((BaseFragment)fragmentManager.findFragmentById(R.id.fragment1));
        fragmentList.add((BaseFragment)fragmentManager.findFragmentById(R.id.fragment2));
        fragmentList.add((BaseFragment)fragmentManager.findFragmentById(R.id.fragment3));

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragmentList){
            if (!fragment.isHidden()){
                transaction.hide(fragment);
            }
        }
        transaction.commit();

        if (savedInstanceState != null){
            selectedIndex = savedInstanceState.getInt("selectedIndex");
        }
        selectFragment(selectedIndex);

        addBroadcastReceiver();//添加广播监听

//        BackCycleService.startService(this);//第一次开始后环执行任务service，之后靠通知循环启动service

        DBManager.copyDB(this, DBManager.ADDRESS_NAME);//导入地区db
        DBManager.copyDB(this, DBManager.WHEEL_NAME);//导入地区db

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        removeBroadcastReceiver();//移除广播监听
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedIndex",selectedIndex);
    }

    //-----------------------------------------------自己方法----------------------------------------------------------------
    public void tabTap(View view){
        switch (view.getId()){
            case R.id.tab0_layout:
                selectedIndex = 0;
                break;
            case R.id.tab1_layout:
                selectedIndex = 1;
                break;
            case R.id.tab2_layout:
                selectedIndex = 2;
                break;
            case R.id.tab3_layout:
                selectedIndex = 3;
                break;
        }
        selectFragment(selectedIndex);
    }

    public void selectFragment(int index){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (BaseFragment fragment : fragmentList){
            if (!fragment.isHidden()){
                transaction.hide(fragment);
            }
        }
        BaseFragment selectedFragment = fragmentList.get(index);
        transaction.show(selectedFragment);
        transaction.commit();
        selectedFragment.tabTap();

        //-------tab img 处理--------------------------------------------------
        for (int i=0;i<4;i++) {
            String imgName = "tab"+i;  //如tab0
            ImageView tabImg = tabImgList.get(i);
            setImageWithName(tabImg,imgName);
        }
        String selectName = "tab"+selectedIndex+"s";//如tab0s
        ImageView tabImg = tabImgList.get(selectedIndex);
        setImageWithName(tabImg, selectName);
    }

    public void addBroadcastReceiver(){

        mainBroadcastReceiver = new MainBroadcastReceiver();
        IntentFilter mainBroadFilter = new IntentFilter();
        mainBroadFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mainBroadFilter.addAction(Task.LOGIN_OUT_ACTION);
        mainBroadFilter.addAction(Task.RECEIVE_SMS_ACTION);
        mainBroadFilter.addAction(Task.SEND_SMS_RESULT_ACTION);
        mainBroadFilter.addAction(Task.BACKGROUND_CYCLE_ACTION);//后台循环执行任务通知
        registerReceiver(mainBroadcastReceiver, mainBroadFilter);
    }

    public void removeBroadcastReceiver(){
        unregisterReceiver(mainBroadcastReceiver);
    }

}
