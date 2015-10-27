package com.example.user.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTxtDisp;
    private Button mBtnClickMe;

    private TextView mTxtId;
    private TextView mTxtName;

    private EditText mEdtId;
    private EditText mEdtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_login);

        mEdtId = (EditText) findViewById(R.id.edtId);
        mEdtName = (EditText) findViewById(R.id.edtName);
        Button btnEnter = (Button) findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(btnEnterClick);

//        Button btnClickMe = (Button) findViewById(R.id.btnClickMe);
//        TextView mTxtDisp = (TextView) findViewById(R.id.txtDisp);
//
//        btnClickMe.setOnClickListener(btnClickMeClick);

//2번 방법
//        mTxtDisp = (TextView) findViewById(R.id.txtDisp);

// 1번 방법
//        Button btnClickMe = (Button) findViewById(R.id.btnClickMe);
//        final TextView txtDisp = (TextView) findViewById(R.id.txtDisp);

//        btnClickMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txtDisp.setText("하이! 절 누르셨군요!1");
//            }
//        });

    }


    private View.OnClickListener btnEnterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,ChatRoomActivity.class);
//            Intent intent = new Intent(getBaseContext(),ChatRoomActivity.class);

//            간단하게 데이터 넘기기
//            intent.putExtra("id",mEdtId.getText().toString());
//            intent.putExtra("name", mEdtName.getText().toString());

            // getter setter 를 이용하여 데이터 넘기기
            Data data = new Data();
            data.setId(mEdtId.getText().toString());
            data.setName(mEdtName.getText().toString());
            intent.putExtra("data", data);

//            intent.setFlags(intent.FLAG_ACTIVITY_NO_HISTORY); // 최초에 설정

//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|intent.FLAG_ACTIVITY_NO_HISTORY); // 추가로 설정값 더해가면서...
            startActivityForResult(intent,100);
//            startActivity(intent);

//            finish(); // 앱 종료
            /*
             * 전화걸기
             */
//            String id = mEdtId.getText().toString();
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + id)); // 다이얼 , ACTION_CALL // 전화걸기
//            startActivity(intent);

            /*
            다른앱 실행하기
             */
//            Intent intent = getPackageManager().getLaunchIntentForPackage("my.geulga");
//            startActivity(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();


        // 전체 패키지명
//        PackageManager pm = this.getPackageManager();
//
//        List<PackageInfo> packs = getPackageManager().getInstalledPackages(PackageManager.PERMISSION_GRANTED);
//
//        for (PackageInfo pack : packs) {
//            Log.i("TEST", pack.applicationInfo.loadLabel(pm).toString());
//            Log.i("TEST", pack.packageName);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * clickMe 버튼 이벤트
     *
     * @param view
     */
//    public void onClickMeClick(View view) {
//        mTxtDisp.setText("하이! 절 누르셨군요2");
//    }

    private View.OnClickListener btnClickMeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mTxtDisp.setText("하이! 절 누르셨군요3");
        }
    };



    /**
     * 액티비티에서 결과를 받아옴.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == 100) {
            String resData = data.getStringExtra("resData");
            Toast.makeText(this, resData, Toast.LENGTH_LONG).show();
        }


    }
}
