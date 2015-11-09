package com.example.user.helloworld.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.helloworld.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2015-11-07.
 */
public class CommonToast {
    private String mImgUrl;
    private Toast mToast;

    public void showToast(Context context, String title, String msg,String imgUrl) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.view_inflate1,null);

        TextView txtTitle = (TextView)view.findViewById(R.id.txtTitle);
        TextView txtContents = (TextView)view.findViewById(R.id.txtContents);
        ImageView imgTitle = (ImageView) view.findViewById(R.id.imgTitle);

        txtTitle.setText(title);
        txtTitle.setText(msg);

        mImgUrl = imgUrl;

        Log.i("TEST", imgUrl);

        mToast = new Toast(context);
        mToast.setGravity(Gravity.TOP, 0, 200);
//        mToast.setDuration(10000);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(view);
//        toast.show();
        
        // 이미지를 처리할경우는 비동기로 처리해야 한다.
        new DownImgTask().execute(imgTitle);
    }
    
    class DownImgTask extends AsyncTask<ImageView, Void, Bitmap> {
        private ImageView mImgView;
        
        @Override
        protected Bitmap doInBackground(ImageView... params) {
            
            mImgView = params[0];

            Bitmap bmp = null;

            try {
                URL url = new URL(mImgUrl);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                InputStream is = con.getInputStream();
                bmp = BitmapFactory.decodeStream(is);

            }catch (Exception e) {
                e.printStackTrace();
            }

            return bmp;
//            return downImage( (String)mImgView.getTag());
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                mImgView.setImageBitmap(bitmap);
            }

            mToast.show();
        }

        private Bitmap downImage(String url) {


            return null;
        }
    }
}
