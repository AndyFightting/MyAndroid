package com.suguiming.myandroid.tab0;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.TextView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

import java.util.concurrent.Executor;

public class AsyncTaskActivity extends BaseActivity {

    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_async_task);
        showTitleView("AsyncTask");
        showLeftImg("back_img");

        textView = (TextView) findViewById(R.id.test_text);
        textView2 = (TextView) findViewById(R.id.test_text2);

        //下面两个任务是异步顺序执行!!!
        AsyncTaskTest testTask1 = new AsyncTaskTest(textView);
        testTask1.execute(1000);
        AsyncTaskTest testTask2 = new AsyncTaskTest(textView2);
        testTask2.execute(500);
    }

    public void btTaped(View view){
        showToast("界面可以响应");
    }


//    Task的实例必须在UI thread中创建；
//    execute方法必须在UI thread中调用；
//    不要手动的调用onPreExecute(), onPostExecute(Result)，doInBackground(Params...), onProgressUpdate(Progress...)这几个方法；
//    该task只能被执行一次，否则多次调用时将会出现异常
    class AsyncTaskTest extends AsyncTask<Integer, Integer, String> {

        private TextView textView;

        public AsyncTaskTest(TextView textV) {
            textView = textV;
        }

    @Override
        protected void onPreExecute() {//在执行实际的后台的doInBackground方法前，被UI 线程调用
            super.onPreExecute();
            textView.setText("开始");
        }

        @Override
        protected String doInBackground(Integer... params) {//在onPreExecute 方法执行后马上执行，该方法运行在后台线程中
            for (int nun = 0; nun < params.length; nun++) {
                for (int i = 0; i <= 5; i++) {
                    publishProgress(i);//调用onProgressUpdate
                    try {
                        Thread.sleep(params[nun]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "执行完毕";//会传递到onPostExecute的参数里
        }

        @Override
        protected void onProgressUpdate(Integer... values) {//这个函数在doInBackground调用publishProgress时被调用后
            super.onProgressUpdate(values);
            textView.setText("更新中: " + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {//在doInBackground 执行完成后，将被UI 线程调用
            super.onPostExecute(s);
            textView.setText(s);
        }

        @Override
        protected void onCancelled() {//取消时被调用
            super.onCancelled();
        }
    }
}
