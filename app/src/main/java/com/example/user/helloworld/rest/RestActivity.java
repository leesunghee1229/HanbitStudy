package com.example.user.helloworld.rest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.helloworld.R;
import com.google.gson.Gson;

import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestActivity extends Activity {

    public static final String URL = "http://ssamhap.aniss.kr/kidsCafeList.do";
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

//        mTxtDisp = (TextView) findViewById(R.id.txtDisp);
        mListView = (ListView) findViewById(R.id.listView);

        new GetTask().execute();
    }

    class GetTask extends AsyncTask<String,Void,Object> {

        // 쓰레드 영역 (통신만 담당)
        @Override
        protected Object doInBackground(String... params) {

            try {
                String url = URL;
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(
                        new MappingJackson2HttpMessageConverter()
                );

                restTemplate.getMessageConverters().add(
                        new FormHttpMessageConverter()
                );
                restTemplate.getMessageConverters().add(
                        new StringHttpMessageConverter()
                );

                // 파라미터를 전송한다.
                CafeBean paramCafeBean = new CafeBean();

                String jsonData = restTemplate.postForObject(url, paramCafeBean, String.class);

                CafeBean cafeBean = new Gson().fromJson(jsonData, CafeBean.class);

                Log.i("TEST", jsonData);

                return cafeBean;

            } catch(Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(o != null) {
                CafeBean cafeBean = (CafeBean)o;

//                List<ListDataBean> list = new ArrayList<cafeBean>();

                RestAdapter restAdapter = new RestAdapter(getBaseContext(), cafeBean);
                mListView.setAdapter(restAdapter);

            }
            else {
                Toast.makeText(RestActivity.this, "통신 실패로 인한 리스트뷰 표시안됨.", Toast.LENGTH_SHORT).show();

            }

//            super.onPostExecute(o);
        }
    };
}
