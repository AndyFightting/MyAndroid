package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.customView.RoundImageView;
import com.suguiming.myandroid.tool.customView.actionSheets.AddressSheet;
import com.suguiming.myandroid.tool.customView.actionSheets.AddressWheelSheet;
import com.suguiming.myandroid.tool.customView.actionSheets.DateSheet;
import com.suguiming.myandroid.tool.customView.actionSheets.ShareSheet;
import com.suguiming.myandroid.tool.customView.actionSheets.TimeSheet;
import com.suguiming.myandroid.tool.utils.ItemTapListener;
import com.suguiming.myandroid.tool.utils.SelectPhotoUtil;

import java.util.Map;

/**
 * Created by suguiming on 15/12/14.
 */
public class ActionSheetActivity extends BaseActivity {

    private RoundImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_action_sheet);
        showTitleView("action sheets");
        showLeftImg("back_img");
        imageView =(RoundImageView)findViewById(R.id.head_img);
    }

    public void shareTap(View view) {
        ShareSheet.show(this, ShareSheet.class, null); //null：也可传入监听ItemTapListener
    }

    public void timeTap(View view) {
        TimeSheet.show(this, TimeSheet.class, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                if (view.getId() == R.id.yes) {
                    Map map = MyTool.getMapFromObj(view.getTag());
                    if (map != null) {
                        String hour = (String) map.get("hour");
                        String minute = (String) map.get("minute");
                        showToast(hour + " 时 " + minute + " 分");
                    }
                }
            }
        });
//        TimeSheet.setTime(23,34); //也可手动设置时间
    }

    public void dateTap(View view){
        DateSheet.show(this, DateSheet.class, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                if (view.getId() == R.id.yes) {
                    Map map = MyTool.getMapFromObj(view.getTag());
                    if (map != null) {
                        String year = (String) map.get("year");
                        String month = (String) map.get("month");
                        String day = (String) map.get("day");
                        showToast(year + " 年 " + month + " 月" + day +" 日");
                    }
                }
            }
        });

//        DateSheet.setDate(2013, 3, 23);//手动设置
//        DateSheet.hideYearPicker();
    }

    public void addressTap(View view){
        AddressSheet.show(this, AddressSheet.class, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                if (view.getId() == R.id.yes) {
                    Map map = MyTool.getMapFromObj(view.getTag());
                    if (map != null) {
                        String one = (String) map.get("one");
                        String two = (String) map.get("two");
                        String three = (String) map.get("three");
                        showToast(one + " " + two + " " + three );
                    }
                }
            }
        });
    }

    public void wheelTap(View view){
        AddressWheelSheet.show(this, AddressWheelSheet.class, new ItemTapListener() {
            @Override
            public void itemTap(View view) {
                if (view.getId() == R.id.yes) {
                    Map map = MyTool.getMapFromObj(view.getTag());
                    if (map != null) {
                        String one = (String) map.get("one");
                        String two = (String) map.get("two");
                        String three = (String) map.get("three");
                        showToast(one + " " + two + " " + three );
                    }
                }
            }
        });
    }

   //头像
    public void imageTap(View view){
        //步骤1
        SelectPhotoUtil.beginSelect(this, new SelectPhotoUtil.ResultListener() {
            @Override
            public void complete(Bitmap bitmap) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //步骤2 (相机，裁剪，相册)
        if (requestCode == Task.TAKE_PHOTO_CODE || requestCode == Task.CROP_PHOTO_CODE || requestCode == Task.ALBUM_CHOOSE_CODE){
            SelectPhotoUtil.processPhoto(requestCode,resultCode,data);
        }
    }
}
