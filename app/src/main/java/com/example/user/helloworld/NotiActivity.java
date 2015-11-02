package com.example.user.helloworld;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class NotiActivity extends Activity {

    private Bitmap mBigBmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);

        mBigBmp = BitmapFactory.decodeResource(getResources(),R.drawable.img_big1);

    }

    public void btnClick(View view) {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Notification noti = null;
        Notification.Builder builder = null;

        Intent intent = new Intent(this,ScrollActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        switch (view.getId()) {
            // BigPicStyle
            case R.id.btn1:
                builder = new Notification.Builder(this);
                builder.setContentTitle("Title 입니다.");
                builder.setContentText("BigPicture 입닌다.");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(mBigBmp);
                builder.setTicker("Ticker 입니다.");
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true); // 해당 알림 클릭하면 사라지게
//                builder.setPriority(Notification.PRIORITY_MAX);

                noti = new Notification.BigPictureStyle(builder)
                        .bigPicture(mBigBmp)
//                        .setBigContentTitle("big pic title")
//                        .setSummaryText("Big text Summary")
                        .build();

                break;
            case R.id.btn2:
                builder = new Notification.Builder(this);
                builder.setContentTitle("Title 입니다.2");
                builder.setContentText("Big Text 입니다.2");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(mBigBmp);
                builder.setTicker("Ticker 입니다.2");
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);

                noti = new Notification.BigTextStyle(builder)
                        .bigText("dddddddddddasfdasfdsefwefwefasdfasdfsdfaasdfwefwefddddd" +
                                "ddddddasfdasfdsefwefwefasdfasdfsdfaasdfwefwefddddddddddda" +
                                "sfdasfdsefwefwefasdfasdfsdfaasdfwefwef")
                        .build();

                break;
            case R.id.btn3:
                builder = new Notification.Builder(this);
                builder.setContentTitle("Title 입니다.3");
                builder.setContentText("Big Text 입니다.3");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(mBigBmp);
                builder.setTicker("Ticker 입니다.3");
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);

                noti = new Notification.InboxStyle(builder)
                        .addLine(" line1 - sdfkjsdlfkjsldkfjlskdjflskdjf")
                        .addLine(" line2 - sdfkjsdlfkjsldkfjlskdjflskdjf")
                        .addLine(" line3 - sdfkjsdlfkjsldkfjlskdjflskdjf")
                        .build();

                break;
            case R.id.btn4:
                builder = new Notification.Builder(this);
                builder.setContentTitle("Title 입니다.4");
                builder.setContentText("Big Text 입니다.4");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(mBigBmp);
                builder.setTicker("Ticker 입니다.4");
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);

                noti = builder.build();

                break;
            default:
                break;
        }


//        noti.defaults |= Notification.DEFAULT_VIBRATE;
//        noti.defaults |= Notification.DEFAULT_SOUND;
//        noti.defaults |= Notification.DEFAULT_LIGHTS;

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
