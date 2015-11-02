package com.example.user.helloworld;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by user on 2015-10-31.
 */
public class MyService extends Service {

    int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        Thread myThread = new Thread(run);
        myThread.start();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return super.onStartCommand(intent, flags, startId);
    }

    private Runnable run = new Runnable() {
        @Override
        public void run() {
//            while (true) {
//
//                Log.i("TEST", "My Service call : " + count);
//                count++;
//
//                try {
//
//                    Thread.sleep(1000);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // 예전에 나온건데... 잘 안쓴다. 다른 방법으로 한다.
        return null;
    }
}
