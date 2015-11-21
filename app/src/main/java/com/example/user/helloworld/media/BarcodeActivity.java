package com.example.user.helloworld.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.user.helloworld.R;

public class BarcodeActivity extends Activity {

    private static final int REQ_CODE_SCAN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);


    }

    public void btnScanClick(View view) {

        Intent intentScan = new Intent("com.google.zxing.client.android.SCAN");
        intentScan.addCategory(Intent.CATEGORY_DEFAULT);

        try {
            startActivityForResult(intentScan,REQ_CODE_SCAN);

        } catch(ActivityNotFoundException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("바코드 스캐너 앱 설치");
            builder.setMessage("바코드 스캐너 앱이 필요합니다. 자동 설치할까요?");
            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Uri uri = Uri.parse("market://details?id=com.google.zxing.client.android");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            builder.create().show();
        }

//        startActivity(intentScan);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == REQ_CODE_SCAN) {
            if(resultCode == Activity.RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");
                String scannedUrl = "";

                if(contents != null && contents.indexOf("https://") >= 0) {
                    int startIndex = contents.indexOf("https://");
                    scannedUrl = contents.substring(startIndex);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("바코드 정보");
                builder.setMessage(contents + "\n" + formatName + "\n" + scannedUrl);
                builder.create().show();
            }
        }
    }
}
