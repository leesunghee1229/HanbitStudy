package com.example.user.helloworld.chat;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by user on 2015-11-07.
 */
public class ChatClient {
    public static final String SERVER_IP = "203.236.209.89";

    private Socket mSocket;
    private DataOutputStream mOutput;
    private String mId, mName;
    private Context mContext;
    private Handler mErrorHandler;

    public void login(Context context, final String id, final String name, final Handler receiveHandler, Handler errorHandler) {

        mErrorHandler = errorHandler;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mSocket = new Socket(SERVER_IP, 7777);

                    // 리시버 접속
                    ClientReceiver clientReceiver = new ClientReceiver(mSocket,receiveHandler);
                    clientReceiver.start();

                    mId = id;
                    mName = name;

                    // 센더 생성
                    mOutput = new DataOutputStream(mSocket.getOutputStream());

                    sendMsg(mName + "님이 대화방에 입장 하셨습니다.");

                } catch (IOException e) {
                    e.printStackTrace();

                    sendErrMsg("서버와의 접속이 원활하지 않습니다. 다시 시도해 주세요.");
                }
            }
        }).start();


    }

    public void sendMsg(String strMsg) {

        if(mOutput != null) {
            try {
                mOutput.writeUTF("[" + mName + "(" + mId + ")] " + strMsg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendErrMsg(String strMsg) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("msg",strMsg);
        msg.setData(bundle);
        mErrorHandler.sendMessage(msg);
    }


    class ClientReceiver extends Thread {
        Socket socket;
        DataInputStream input;
        Handler receiveHandler;

        public ClientReceiver(Socket socket, Handler receiveHandler) {
            this.socket = socket;
            this.receiveHandler = receiveHandler;

            try {
                input = new DataInputStream(socket.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        @Override
        public void run() {
            while(input != null) {
                try {
                    String recStr = input.readUTF();

                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("msg",recStr);
                    msg.setData(bundle);

                    receiveHandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
