package com.example.user.helloworld.gcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.user.helloworld.R;
import com.example.user.helloworld.ScrollActivity;
import com.example.user.helloworld.utils.CommonToast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by user on 2015-11-07.
 */
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.d("TEST", action);

        if(action != null && action.equals("com.google.android.c2dm.intent.RECEIVE")) {
            // 서버에서 보내준 키값의 데이터를 취득한다.
            String type = intent.getStringExtra("type");
            String command = intent.getStringExtra("command");
            String title = intent.getStringExtra("title");
            String data = intent.getStringExtra("data");
            String imgUrl = intent.getStringExtra("imgUrl");
            String decodeData = "";
            String decodeData2 = "";

            try {
                decodeData = URLDecoder.decode(data,"UTF-8");
                decodeData2 = URLDecoder.decode(title,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            Log.d("TEST", type + ", " + command + ", " + decodeData + ", " + decodeData2 + ", " + imgUrl);

            showNoti(context, decodeData2, decodeData);

            CommonToast commonToast = new CommonToast();
            commonToast.showToast(context,decodeData2,decodeData,imgUrl);

//            Toast.makeText(context, decodeData, Toast.LENGTH_SHORT).show();
        }
    }

    public void showNoti(Context context, String title, String content) {

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Notification noti = null;
        Notification.Builder builder = null;

        Intent intent = new Intent(context, ScrollActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder = new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("Ticker 입니다.4");
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        noti = builder.build();

        // Vibe
        long pattern[] = {500,500};
        noti.vibrate = pattern;

        // Ton
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if(alarmSound == null) {
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        noti.sound = alarmSound;

        // LED
        noti.ledARGB = 0XFF0000FF; // blue
        noti.flags = Notification.FLAG_SHOW_LIGHTS;
        noti.ledOnMS = 400;
        noti.ledOffMS = 300;

        manager.notify((int)System.currentTimeMillis(), noti);
    }
}
