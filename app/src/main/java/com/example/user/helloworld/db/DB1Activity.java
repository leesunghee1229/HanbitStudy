package com.example.user.helloworld.db;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.helloworld.R;

public class DB1Activity extends Activity {

    private Button mBtnDB, mBtnTable;
    private EditText mEdtDB, mEdtTable;
    private TextView mTxtStatus;
    private SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db1);

        mBtnDB = (Button) findViewById(R.id.btnDB);
        mBtnTable = (Button) findViewById(R.id.btnTable);
        mEdtDB = (EditText) findViewById(R.id.edtDB);
        mEdtTable = (EditText) findViewById(R.id.edtTable);
        mTxtStatus = (TextView) findViewById(R.id.txtStatus);

    }

    /**
     * 디비클릭
     *
     * @param view
     */
    public void btnDBClick(View view) {

        switch (view.getId()) {
            case R.id.btnDB:
                // 디비 생성
                mDB = openOrCreateDatabase(mEdtDB.getText().toString(), MODE_WORLD_WRITEABLE, null);
                printStatus("디비 생성 완료");
                break;
            case R.id.btnTable:
                if (mDB == null) return;

                String tableName = mEdtTable.getText().toString();

                try {
                    mDB.execSQL("CREATE TABLE " + tableName
                                    + "( _id integer PRIMARY KEY autoincrement, "
                                    + " name text, "
                                    + " age integer, "
                                    + " phone text); "
                    );

                    printStatus("테이블 생성 완료");
                    mDB.execSQL("INSERT INTO "+tableName
                            + " (name, age, phone) VALUES "
                            + " ('Jhone',20,'011-1234-1234');"
                    );
                    mDB.execSQL("INSERT INTO "+tableName
                            + " (name, age, phone) VALUES "
                            + " ('Jhone1',21,'012-1234-1234');"
                    );
                    mDB.execSQL("INSERT INTO "+tableName
                            + " (name, age, phone) VALUES "
                            + " ('Jhone2',22,'013-1234-1234');"
                    );
                    printStatus("데이터 3건 입력 성공");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    private void printStatus(String str) {
        mTxtStatus.append(str+"\n");
    }
}
