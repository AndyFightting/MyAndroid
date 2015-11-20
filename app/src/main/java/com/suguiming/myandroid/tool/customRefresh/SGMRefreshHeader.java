package com.suguiming.myandroid.tool.customRefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.tool.MyTool;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Created by suguiming on 15/11/20.
 */

//用刷新框架，这里是自定义刷新头部 ------------
public class SGMRefreshHeader extends RelativeLayout implements PtrUIHandler {

    private TextView titleTv;
    private boolean reachRefreshFlag; //是否到了可以刷新的伐值
    private float flag;//刷新转折点伐值
    private int headViewHeightDip;
    private Context mContext;

    public SGMRefreshHeader(Context context){
        super(context);
        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.refresh_header, this);
        titleTv = (TextView) view.findViewById(R.id.title);
    }

    public void onUIRefreshPrepare(PtrFrameLayout var1){ //准备开始下拉
        titleTv.setText("下拉刷新");
        reachRefreshFlag = false;

        headViewHeightDip = MyTool.pixFromDip(mContext,60);
        flag = var1.getRatioOfHeaderToHeightRefresh()*headViewHeightDip;
    }

    public void onUIRefreshBegin(PtrFrameLayout var1){

        titleTv.setText("正在刷新...");

    }

    public void onUIRefreshComplete(PtrFrameLayout var1){
        titleTv.setText("刷新完成");

    }

    public void onUIPositionChange(PtrFrameLayout var1, boolean isTouch, byte var3, PtrIndicator var4){
        int offsetY = var4.getCurrentPosY();//下拉距离pix
        if (isTouch){
            if (offsetY > flag){
                if (!reachRefreshFlag){
                    reachRefreshFlag = true;
                    //可以刷新的转折点--------
                    titleTv.setText("释放立即刷新");
                }
            }else {
                if (reachRefreshFlag){
                    reachRefreshFlag = false;
                    //不可以刷新的转折点-------
                    titleTv.setText("下拉刷新");
                }
            }
        }
    }

    public void onUIReset(PtrFrameLayout var1){//手放开后重置，在onUIRefreshPrepare里处理就可以了

    }
}
