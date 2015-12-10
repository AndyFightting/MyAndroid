package com.suguiming.myandroid.tool.customView;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.adapter.CBPageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.suguiming.myandroid.R;

public class BannerNetworkHolder implements CBPageAdapter.Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        imageView.setImageResource(R.mipmap.ic_default_adimage);
        ImageLoader.getInstance().displayImage(data, imageView);
    }
}
