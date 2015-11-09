package com.example.user.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.helloworld.chat.ChatClient;

public class ChatRoomActivity extends AppCompatActivity {

    private TextView mTxtId, mTxtName,mTxtUserCounter;
    private EditText mEdtChat, mEdtChatText;
    private Button mBtnSend;

    private ChatClient mChatClient;
    private ScrollView mScvChatText;

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
        mScvChatText = (ScrollView) findViewById(R.id.scvChatText);

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

        { // 채팅 소스
            // 서버와의 접속을 실행한다.
            mChatClient = new ChatClient();
            // 로그인
            mChatClient.login(this, data.getId(), data.getName(), mReceiveHandler, mErrorHandler );

            mBtnSend.setOnClickListener(mBtnSendClick);
        }



//        intent.setClass(this, MainActivity.class);
//        Intent newIntent = new Intent(this,MainActivity.class);
//        newIntent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP|
//        Intent.FLAG_ACTIVITY_NEW_TASK); // 서비스단에서 화면 호출할때 필요함
//        startActivity(newIntent);


//        Intent newIntent = new Intent();
//        newIntent.putExtra("resData","성공");
//        setResult(RESULT_OK,newIntent);
//        finish();
    }

    // 메시지를 서버로부터 받는 핸들러
    private Handler mReceiveHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            String strMsg = msg.getData().getString("msg");

            // 서버로부터의 커맨드 메시지를 구분한다. 구분자 : @
            String commandCount = "@count";
            if(strMsg != null && strMsg.startsWith(commandCount)) {
                mTxtUserCounter.setText("현재 접속자수 : " + strMsg.substring(commandCount.length()));
                return;
            }

            mEdtChatText.setText(mEdtChatText.getText().toString() + "\n"+strMsg);

            // 스크롤을 제일 밑으로 내려주는 처리 실행
            mScvChatText.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScvChatText.smoothScrollTo(0,mEdtChatText.getBottom());
                    mEdtChat.requestFocus();
                }
            },100);
        }
    };

    private Handler mErrorHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String strMsg = msg.getData().getString("msg");
            Toast.makeText(ChatRoomActivity.this, strMsg, Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener mBtnSendClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 서버로 메시지를 보낸다.
            mChatClient.sendMsg( mEdtChat.getText().toString() );
            mEdtChat.setText("");
            mEdtChat.requestFocus();
        }
    };
}
