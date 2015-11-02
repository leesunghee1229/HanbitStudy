package com.example.user.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 2015-10-31.
 */
public class MyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TEST", intent.getAction());

        // 문자 통지를 처리하겠다.
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Intent newIntent = new Intent(context, ScrollActivity.class);
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 태스크가 꼬일수 있으니 새롭게 태스크를 만든다.
            context.startActivity(newIntent);
        }

        // 전화
        if(intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            String phoneNum = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context, "Calling : " + phoneNum, Toast.LENGTH_SHORT).show();
        }
    }
}
