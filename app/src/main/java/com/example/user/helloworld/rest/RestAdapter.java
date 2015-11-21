package com.example.user.helloworld.rest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.helloworld.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2015-11-21.
 */
public class RestAdapter extends BaseAdapter {
    private Context mContext;
    private CafeBean mCafeBean;

    public RestAdapter(Context context, CafeBean cafeBean) {
        mContext = context;
        mCafeBean = cafeBean;
    }

    @Override
    public int getCount() {
        return mCafeBean.getListCafe().size();
    }

    @Override
    public Object getItem(int position) {
        return mCafeBean.getListCafe().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.i("TEST", "listAdapater==> postion:" + position + ", convertView:" + convertView);

        LayoutInflater inflater  =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.view_inflate1, null);

        ImageView imgTitle = (ImageView) convertView.findViewById(R.id.imgTitle);
        TextView txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView txtContents = (TextView)convertView.findViewById(R.id.txtContents);


        //데이터를 뽑아온다.
        CafeBean bean = mCafeBean.getListCafe().get(position);
        //각 뷰에 맞는 데이터를 셋팅한다.
        txtTitle.setText(bean.getKcTheme());
        txtContents.setText(bean.getKcName());
        new DownImgTask(bean.getImgUrl()).execute(imgTitle);

        convertView.setTag(position);

        return convertView;
    }

    // 이미지 비동기 다운로드 처리
    class DownImgTask extends AsyncTask<ImageView, Void, Bitmap> {
        private ImageView mImgView;
        private String mImgUrl;
        public DownImgTask(String imgUrl) {
            mImgUrl = imgUrl;
        }

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

//            mToast.show();
        }

        private Bitmap downImage(String url) {


            return null;
        }
    }


}
