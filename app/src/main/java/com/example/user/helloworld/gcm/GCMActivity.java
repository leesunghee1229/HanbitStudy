package com.example.user.helloworld.gcm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.user.helloworld.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class GCMActivity extends Activity {

    public static final String PROJECT_ID = "831230466122";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gcm);
    }

    public void onBtnGcmRegClick(View view) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(GCMActivity.this);

                try {
                    String regId = gcm.register(PROJECT_ID);

                    Log.d("TEST", "regid:"+regId);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
