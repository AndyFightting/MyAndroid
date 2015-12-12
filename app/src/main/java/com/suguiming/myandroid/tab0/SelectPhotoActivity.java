package com.suguiming.myandroid.tab0;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.Task;
import com.suguiming.myandroid.tool.customView.RoundImageView;
import com.suguiming.myandroid.tool.utils.SelectPhotoUtil;

/**
 * Created by suguiming on 15/12/12.
 */
public class SelectPhotoActivity extends BaseActivity {

    private RoundImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_select_photo);
        showTitleView("相机，相册");
        showLeftImg("back_img");

        imageView =(RoundImageView)findViewById(R.id.head_img);

    }

    public void imageTap(View view){
        //步骤1
        SelectPhotoUtil.beginSelect(this, new SelectPhotoUtil.ResultListener() {
            @Override
            public void complete(Bitmap bitmap) {
                if (bitmap != null){
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
