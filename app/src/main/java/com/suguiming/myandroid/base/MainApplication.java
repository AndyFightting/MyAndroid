package com.suguiming.myandroid.base;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.Logger;
import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.volley.MultiPartStack;

/**
 * Created by suguiming on 15/11/18.
 */
public class MainApplication extends Application {

    public static final String TAG = MainApplication.class.getSimpleName();
    private static MainApplication application;
    private RequestQueue queue;//volley queue

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        Logger.init("myLog");
        initImageLoader();
    }

    public static synchronized MainApplication getApplication(){
        return application;
    }

    public RequestQueue getQueue(){
        if (queue == null){
            queue = Volley.newRequestQueue(getApplication(),new MultiPartStack());
        }
        return queue;
    }

    public <T> void addToQueue(Request<T> request){
        request.setTag(TAG);
        getQueue().add(request);
    }

    public <T> void addToQueueWithTag(Request<T> request, String tag) {
        request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getQueue().add(request);
    }

    public void cancelRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }

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

}