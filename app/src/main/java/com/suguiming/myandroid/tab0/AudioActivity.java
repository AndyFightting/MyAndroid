package com.suguiming.myandroid.tab0;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import com.suguiming.myandroid.R;
import com.suguiming.myandroid.base.BaseActivity;

/**
 * Created by suguiming on 15/12/13.
 */
public class AudioActivity extends BaseActivity {

    private MediaPlayer mp3Player = new MediaPlayer();
    private VideoView videoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMainView(R.layout.audio_activity);
        showTitleView("音频，视频");
        showLeftImg("back_img");

        initMP3();
        initVideo();
    }

    private void initVideo() {
        videoView = (VideoView) findViewById(R.id.video_view);

        // /res/raw 文件夹里
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        videoView.setVideoURI(videoUri);
    }

    private void initMP3() {
        try {
            //读 /main/assets 文件夹里的资源
            AssetManager assetManager = getResources().getAssets();
            AssetFileDescriptor fileDescriptor = assetManager.openFd("music.mp3");
            mp3Player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
            mp3Player.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playTap(View view) {
        if (!mp3Player.isPlaying()) {
            mp3Player.start();
        }
    }

    public void pauseTap(View view) {
        if (mp3Player.isPlaying()) {
            mp3Player.pause();
        }
    }

    public void stopTap(View view) {
        if (mp3Player.isPlaying()) {
            mp3Player.reset();
            initMP3();
        }
    }

    public void mp4PlayTap(View view) {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    public void mp4PauseTap(View view) {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
    }

    public void mp4StopTap(View view) {
        if (videoView.isPlaying()) {
            videoView.resume();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mp3Player != null) {
            mp3Player.stop();
            mp3Player.release();
        }
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
