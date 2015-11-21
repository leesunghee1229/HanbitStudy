package com.example.user.helloworld.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.user.helloworld.R;

public class AudioPlayActivity extends Activity {

    private static final String AUDIO_URL = "http://www.tonycuffe.com/mp3/saewill.mp3";
    private MediaPlayer mPlayer;
    private int mPlayBackPosition = 0; // 마지막 재생위치 저장 변수
    private MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mMediaController != null) {
            mMediaController.show();
        }
        return super.onTouchEvent(event);
    }

    public void onBtnAutdio(View view) {
        try {
            int id = view.getId();
            switch (id) {
                case R.id.btnPlay:
                    killMediaPlayer();
                    mPlayer = new MediaPlayer();

                    // url 재생
                    mPlayer.setDataSource(AUDIO_URL);

                    // asset 재생
//                    AssetFileDescriptor descriptor = getAssets().openFd("refreshing_sound.mp3");
//                    mPlayer.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
//                    descriptor.close();

                    mPlayer.setOnPreparedListener(onPreparedListener);

                    mPlayer.prepare(); // 재생준비
                    mPlayer.setVolume(1f, 1f);
                    mPlayer.setLooping(true); // 무한루프
                    mPlayer.start(); // 재생

                    Toast.makeText(AudioPlayActivity.this, "음악재생 시작", Toast.LENGTH_SHORT).show();

                    break;
                case R.id.btnStop: // 일시 정지
                    mPlayBackPosition = mPlayer.getCurrentPosition(); // 진행중인위치
                    mPlayer.pause();
                    Toast.makeText(AudioPlayActivity.this, "음악재생 중지", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btnRestart:
                    mPlayer.start();
                    mPlayer.seekTo(mPlayBackPosition); // 마지막 저장 위치로 이동

                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    private void killMediaPlayer() {
        if(mPlayer != null) {
            mPlayer.release();
        }
    }

    private MediaPlayer.OnPreparedListener onPreparedListener = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            // MediaController 를 활성화 시킨다.
            mMediaController = new MediaController(AudioPlayActivity.this);
            mMediaController.setMediaPlayer(controller);
            mMediaController.setAnchorView(findViewById(R.id.mediaController));
            mMediaController.show(0);
        }
    };

    private MediaController.MediaPlayerControl controller = new MediaController.MediaPlayerControl() {
        @Override
        public void start() {
            mPlayer.start();
        }

        @Override
        public void pause() {
            mPlayer.pause();
        }

        @Override
        public int getDuration() {
            return mPlayer.getDuration();
        }

        @Override
        public int getCurrentPosition() {
            return mPlayer.getCurrentPosition();
        }

        @Override
        public void seekTo(int pos) {
            mPlayer.seekTo(pos);
        }

        @Override
        public boolean isPlaying() {
            return mPlayer.isPlaying();
        }

        @Override
        public int getBufferPercentage() {
            return 0;
        }

        @Override
        public boolean canPause() {
            return false;
        }

        @Override
        public boolean canSeekBackward() {
            return false;
        }

        @Override
        public boolean canSeekForward() {
            return false;
        }

        @Override
        public int getAudioSessionId() {
            return 0;
        }
    };
}
