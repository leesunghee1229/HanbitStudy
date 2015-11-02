package com.example.user.helloworld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class DigActivity extends AppCompatActivity {

    private Button mBtnDlg1, mBtnDlg2;
    private ProgressDialog pDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig);

        mBtnDlg1 = (Button) findViewById(R.id.btnDlg1);
        mBtnDlg2 = (Button) findViewById(R.id.btnDlg2);


    }

    public void btnDlg1Click(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("안내");
        builder.setMessage("종료하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_info);

        // 예 버튼
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DigActivity.this, "예 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 아니요 버튼
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DigActivity.this, "아니오 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 취소 버튼
        builder.setNeutralButton("취소", null);

        AlertDialog dlg = builder.create();
        dlg.setCanceledOnTouchOutside(false); // 밖에 영역 클릭하면 취소 되도록 true
        dlg.setCancelable(false); // 백버튼을 눌러도 취소 안되도록
        dlg.show();
    }

    public void btnDlg2Click(View view) {
        pDlg = new ProgressDialog(this);
        pDlg.setMessage("잠시만 기다려주세요...");
        pDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDlg.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (i <= 5) {
                    pDlg.setProgress(i * 10 * 2);
                    i++;
                    Message msg = new Message();
                    Bundle data = new Bundle();
                    msg.arg1 = i * 10 * 2;
                    data.putString("msg", "현재 진행상태 : " + (i * 10 * 2) + "%");
                    msg.setData(data);
                    handler.sendMessageDelayed(msg, 500);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
//                pDlg.dismiss(); // 다이얼로그 닫기
//                    pDlg.cancel();
            }
        }).start();

    }

    // 다른 layout xml 에서 가져와 다이얼로그로 띄움
    public void btnDlg3Click(View view) {
        final Dialog dlg = new Dialog(this);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀을 없앤다.
        dlg.setContentView(R.layout.view_inflate1);

        Button btnView = (Button) dlg.findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.dismiss();
            }
        });

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
//        dlg.show();
    }

    public void btnDlg4Click(View view) {
        Log.d("TEST", "btnDlg4Click");
        new ProgressTask().execute();
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle data = msg.getData();
            String strMsg = data.getString("msg");

            pDlg.setMessage(strMsg);
            pDlg.setProgress(msg.arg1);
        }
    };

    class ProgressTask extends AsyncTask<String, Integer, Integer> {
        ProgressDialog dlg;

        @Override
        protected void onPreExecute() {
            // 쓰레드 영역이 아니다.
            Log.d("TEST", "onPreExecute");
            dlg = new ProgressDialog(DigActivity.this);
            dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dlg.show();

//            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(String... params) {
            // 쓰레드 영역
            // 만약에 ImageView 나 메인쓰레드 영역의 객체를 업데이트 시키면
            // 앱이 죽는다.
            Log.d("TEST", "doInBackground");
            int i = 1;
            while (i <= 100) {

                publishProgress(i); // 쓰레드 영역에서 메인쓰레드 영역으로 내보낸다.
                // --> onProgressUpdate 가 호출됨

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;
            }


            return 100;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            // 메인 쓰레드 영역

            dlg.setProgress(values[0]);

//            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            // doInBackground 가 종료되면 호출되는 메서드
            if (integer == 100) {
                dlg.dismiss();
            }

//            super.onPostExecute(integer);
        }
    }


}
