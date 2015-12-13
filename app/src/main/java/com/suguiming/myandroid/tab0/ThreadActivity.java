package com.suguiming.myandroid.tab0;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by suguiming on 15/12/13.
 */
public class ThreadActivity extends BaseActivity {

    private TextView titleTv;
    private MyHandler handler;
    private MyAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_thread);
        showTitleView("Thread,Service");
        showLeftImg("back_img");

        titleTv = (TextView) findViewById(R.id.title);

        ThreadOne threadOne = new ThreadOne();
        threadOne.start();

        ThreadTwo tmpThread = new ThreadTwo();
        Thread thread = new Thread(tmpThread);
        thread.start();

        //方式三
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i("thread three", "hello world");
            }
        }).start();

        handler = new MyHandler(this);
        asyncTask = new MyAsyncTask();
        asyncTask.execute();
    }

    public void btTap(View view) {
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);

    }

    //方式一
    class ThreadOne extends Thread {
        @Override
        public void run() {
            Log.i("thread one", "hello world");
        }
    }

    //方式二
    class ThreadTwo implements Runnable {
        @Override
        public void run() {
            Log.i("thread two", "hello world");
        }
    }

    //Handler
    static class MyHandler extends Handler {
        WeakReference<ThreadActivity> reference;

        MyHandler(ThreadActivity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ThreadActivity activity = reference.get();

            switch (msg.what) {
                case 1:
                    activity.titleTv.setText("handle message");
                    break;
            }
        }
    }

    //AsyncTask
    class MyAsyncTask extends AsyncTask<Void,Integer,Boolean>{ //Params, Progress, Result
        @Override
        protected void onPreExecute() {//初始化
               showToast("async task 开始");
        }

        @Override
        protected Boolean doInBackground(Void... params) {//处理耗时任务,返回类型就是上面的第三个参数类型！！
            publishProgress(0);//第二个参数类型，这样通知 onProgressUpdate() 更新UI
            try {
                Thread.sleep(10000);//休眠10秒
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) { //更新UI操作
            showToast("async task 处理中");
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) { //doInBackground() 执行完毕被调用
            if (aBoolean){
                showToast("async task 成功");
            }else {
                showToast("async task 失败");
            }
        }
    }


}
