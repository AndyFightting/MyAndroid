package com.suguiming.myandroid.login;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;
import com.suguiming.myandroid.tool.MyTool;
import com.suguiming.myandroid.tool.waveView.WaveView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends BaseActivity {

    private SeekBar seekBar;
    private WaveView waveView;
    private int progress;


    private MyHandler myHandler;
    private TimerTask task;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.activity_login);
        showTitleView("登录");
        myHandler = new MyHandler(this);

        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        waveView = (WaveView) findViewById(R.id.wave_view);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        task = new TimerTask( ) {
            public void run ( ) {
                Message message = new Message( );
                message.what = 0;
                myHandler.sendMessage(message);
            }
        };

        timer = new Timer();
        timer.schedule(task,0,100);
    }

    private void updateView(){
       if (progress >= 100){
           progress = 100;
           seekBar.setProgress(progress);
           timer.cancel();
       }else {
           progress = progress + 2;
           seekBar.setProgress(progress);
       }
    }

    //-------------Handler class---------------
    static class MyHandler extends Handler {
        WeakReference<LoginActivity> weakReference;

        MyHandler(LoginActivity activity) {
            weakReference = new WeakReference<>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            LoginActivity activity = weakReference.get();
            switch (msg.what) {
                case 0:
                    activity.updateView();
                    break;
            }
        }
    }
}
