package com.example.user.helloworld.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.helloworld.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class VideoRecActivity extends Activity {

    private static final String REC_FILE = "videoRec.mp4";

    private MediaPlayer mPlayer;
    private MediaRecorder mRecorder;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_rec);

        SurfaceView sfView = new SurfaceView(this);
        mHolder = sfView.getHolder();
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        FrameLayout layVideo = (FrameLayout) findViewById(R.id.layVideo);
        layVideo.addView(sfView);
    }

    public void onBtnClick(View view) {
        try {
            switch(view.getId()) {
                case R.id.btnRec:
                    if(mRecorder != null) {
                        mRecorder.stop();
                        mRecorder.release();
                        mRecorder = null;
                    }

                    mRecorder = new MediaRecorder();
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    // 비디오 추가
                    mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4); //옵션을 바꾸면 화질을 개선할 수 있다.
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

                    // 저장장소를 추가해준다.
                    // 내부저장: /data/data/com.example.user.helloworld/files/videoRec.mp4
                    FileOutputStream outStream = openFileOutput(REC_FILE, MODE_PRIVATE);
                    mRecorder.setOutputFile(outStream.getFD());

                    // 미리보기 부분 추가
                    mRecorder.setPreviewDisplay(mHolder.getSurface());

                    mRecorder.prepare();
                    mRecorder.start();

                    break;
                case R.id.btnRecStop:
                    if(mRecorder == null) return;

                    mRecorder.stop();
                    mRecorder.release();
                    mRecorder = null;

                    // 앨범에 등록하는 부분
                    // ContentValues => 안드로이드 갤러리


                    break;
                case R.id.btnPlay:
                    if(mPlayer != null) {
                        mPlayer.stop();
                        mPlayer.release();
                        mPlayer = null;
                    }

                    mPlayer = new MediaPlayer();

                    // 내부 저장된 파일을 읽어 온다.
                    // 내부저장: /data/data/com.example.user.helloworld/files/videoRec.mp4
                    FileInputStream inStream = openFileInput(REC_FILE);
                    mPlayer.setDataSource( inStream.getFD() );
                    // 서피스뷰에 재생을 시킨다.
                    mPlayer.setDisplay(mHolder);
                    mPlayer.prepare();
                    mPlayer.start();

                    break;
                case R.id.btnStop:
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
