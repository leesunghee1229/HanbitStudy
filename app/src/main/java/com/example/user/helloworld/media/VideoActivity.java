package com.example.user.helloworld.media;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.user.helloworld.R;

public class VideoActivity extends Activity {

    private final static String VIDEO_URL = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    private VideoView mVideoView;
    private MediaController mMC;
    private ProgressDialog mDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoView = (VideoView) findViewById(R.id.videoView);
        mMC = new MediaController(this);
        mVideoView.setMediaController(mMC);

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp, int percent) {

                        Log.i("TEST", String.valueOf(percent));

//                        Toast.makeText(VideoActivity.this, "Buffering :" + percent+"%", Toast.LENGTH_SHORT).show();
                        mDlg.cancel();
                    }
                });
            }
        });
    }

    // 비디오 재생버튼.
    public void onBtnPlayClick(View view) {
        mDlg = new ProgressDialog(this);
        mDlg.setMessage("비지오 재생 준비중...");
        mDlg.show();

        mVideoView.setVideoURI(Uri.parse(VIDEO_URL));
        mVideoView.requestFocus();
        mVideoView.start();
    }

    public void onBtnVolumeClick(View view) {
        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume, AudioManager.FLAG_SHOW_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mVideoView.stopPlayback();
    }
}
