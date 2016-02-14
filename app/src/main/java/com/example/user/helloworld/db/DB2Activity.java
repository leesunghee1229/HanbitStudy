package com.example.user.helloworld.db;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.helloworld.R;

import java.net.URL;

public class DB2Activity extends Activity {

    private ImageView mImgView;
    private TextView mTxtStatus;
    public static String IMG_URL = "http://uf.demoday.co.kr/banner_images/DorYYv5sl8f9iPtlntAHgi2DYFi1MOQkfBpblohlfzlEK8XoH9.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db2);

        mImgView = (ImageView)findViewById(R.id.imgView);
        mTxtStatus = (TextView)findViewById(R.id.txtStatus);
    }

    //DB생성 이벤트
    public void onBtnDBClick(View view) {

        DBHelper helper = DBHelper.getInstance(this);
        helper.openDB();

        //insert할 데이터가 있으면
        helper.insertData();

        //이미지 다운을 받아서 새로운 행을 Insert 시킨다.
        new ImgDown().execute(IMG_URL);
    }

    //조회 이벤트
    public void onBtnSearchClick(View view) {

        DBHelper helper = DBHelper.getInstance(this);

        //1번째 조회
//        Cursor c = helper.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME1 );
        Cursor c = helper.rawQuery2("SELECT * FROM " + DBHelper.TABLE_NAME1 + " WHERE name = ?"
                , new String[]{"Michael"}
        );

        int nameIdx = c.getColumnIndex("name");
        int ageIdx = c.getColumnIndex("age");
        int phoneIdx = c.getColumnIndex("phone");


        for(int i=0; i<c.getCount(); ++i) {
            c.moveToNext();
            String name = c.getString(nameIdx);
            int age = c.getInt(ageIdx);
            String phone = c.getString(phoneIdx);

            printStatus("Row " + i+1 + "# " + name + ", " + age + ", " + phone);
        }

        mImgView.setImageBitmap( helper.getBmpImg() );
    }

    private void printStatus(String str) {
        mTxtStatus.append(str + "\n");
    }

    //원격 이미지 다운로드
    private class ImgDown extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap bmp = null;

            try {
                URL url = new URL(params[0]);

                //이미지 줄이기
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inJustDecodeBounds = false;
//                option.inSampleSize = 4; //4/1로 줄인다
                bmp = BitmapFactory.decodeStream( url.openStream(), null, option );

            } catch(Exception e) {
                e.printStackTrace();
            }

            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //DB에 저장
            DBHelper helper = DBHelper.getInstance(getBaseContext());
            helper.insertBitmap(bitmap);
        }
    }




}
