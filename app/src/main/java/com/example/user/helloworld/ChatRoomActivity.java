package com.example.user.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChatRoomActivity extends AppCompatActivity {

    private TextView mTxtId, mTxtName,mTxtUserCounter;
    private EditText mEdtChat, mEdtChatText;
    private Button mBtnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        mTxtId = (TextView) findViewById(R.id.txtId);
        mTxtName = (TextView) findViewById(R.id.txtName);
        mTxtUserCounter = (TextView) findViewById(R.id.txtUserCount);
        mEdtChat = (EditText) findViewById(R.id.edtChat);
        mEdtChatText = (EditText) findViewById(R.id.edtChatText);
        mBtnSend = (Button) findViewById(R.id.btnSend);

        // 이 화면을 호출한 액태비티의 intent 를 얻는다.
        Intent intent = getIntent(); // 상대방이 넘겨준 intent 를 받기 때문에 new 를 하지 않는다.
//        String id = intent.getStringExtra("id"); // getIntExtra(); ,
//        String name = intent.getStringExtra("name");

        Data data = (Data) intent.getSerializableExtra("data");


//        Log.d("hb", id);
//        Log.d("hb", name);

//        mTxtId.setText(id);
//        mTxtName.setText(name);
        mTxtId.setText(data.getId());
        mTxtName.setText(data.getName());

//        intent.setClass(this, MainActivity.class);
//        Intent newIntent = new Intent(this,MainActivity.class);
//        newIntent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP|
//        Intent.FLAG_ACTIVITY_NEW_TASK); // 서비스단에서 화면 호출할때 필요함
//        startActivity(newIntent);


        Intent newIntent = new Intent();
        newIntent.putExtra("resData","성공");
        setResult(RESULT_OK,newIntent);
        finish();
    }
}
