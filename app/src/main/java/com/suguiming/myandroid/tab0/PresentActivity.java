package com.suguiming.myandroid.tab0;

import android.os.Bundle;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.adapter.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.customView.BannerLocalHolder;
import com.suguiming.myandroid.tool.customView.BannerNetworkHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PresentActivity extends BaseActivity implements OnItemClickListener {

    private ConvenientBanner banner;
    private ArrayList<Integer> localImages = new ArrayList<>();
    private List<String> networkImages;
    private String[] urlArray = {
            "http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_present);
        showTitleView("iOS present view");
        showLeftImg("back_img");

        initImageLoader();

        showNetworkImageBanner();
//        showLocalImageBanner();
    }

    //一般放 Application 里初始化
    private void initImageLoader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.ic_default_adimage)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();

        ImageLoader.getInstance().init(config);
    }

    private void showLocalImageBanner() {
        banner = (ConvenientBanner) findViewById(R.id.banner);
        for (int position = 0; position < 7; position++) {
            localImages.add(MyTool.getResIdByName("ic_test_" + position, R.mipmap.class));
        }

        banner.setPages(
                new CBViewHolderCreator<BannerLocalHolder>() {
                    @Override
                    public BannerLocalHolder createHolder() {
                        return new BannerLocalHolder();
                    }
                }, localImages);

        banner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        banner.setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);//滚动样式
        banner.setOnItemClickListener(this);
    }

    private void showNetworkImageBanner() {
        banner = (ConvenientBanner) findViewById(R.id.banner);
        networkImages = Arrays.asList(urlArray);

        banner.setPages(new CBViewHolderCreator<BannerNetworkHolder>() {
            @Override
            public BannerNetworkHolder createHolder() {
                return new BannerNetworkHolder();
            }
        }, networkImages);

        banner.setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused});
        banner.setPageTransformer(ConvenientBanner.Transformer.DefaultTransformer);//滚动样式
        banner.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position) {
        showToast("点击" + position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        banner.startTurning(2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.stopTurning();
    }
}
